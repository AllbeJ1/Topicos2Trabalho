package br.com.notify.med;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class NovaMedicacaoActivity extends AppCompatActivity {
    private Button btnAddMedicacao, btnVoltarMedicacao;

    private TextInputEditText textInputEditTextNomeMedicacao, textInputEditTextTipo, textInputEditTextQuantidade,
            textInputEditTextHorario, textInputEditTextDuracao;

    //Banco de Dados
    //private DatabaseReference BD = FirebaseDatabase.getInstance().getReference();

    // Adapter da lista de medicacoes
    AdapterMedicacao adapterMedicacoes;
    // Cursor com os dados recuperados do BD
    Cursor cursorMedicacoes;
    // Referência para o banco de dados
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_medicacao);
        this.textInputEditTextNomeMedicacao = findViewById(R.id.textInputEditTextNomeMedicacao);
        this.textInputEditTextTipo = findViewById(R.id.textInputEditTextTipo);
        this.textInputEditTextQuantidade = findViewById(R.id.textInputEditTextQuantidade);
        this.textInputEditTextHorario = findViewById(R.id.textInputEditTextHorario);
        this.textInputEditTextDuracao = findViewById(R.id.textInputEditTextDuracao);

        this.btnAddMedicacao = findViewById(R.id.btnAddMedicacao);
        this.btnVoltarMedicacao = findViewById(R.id.btnVoltarMedicacao);

        // Abrindo ou criando o banco de dados
        bd = openOrCreateDatabase( "safetymed_bd", MODE_PRIVATE, null );

        this.btnAddMedicacao.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(!validaDados()){
                    Toast.makeText(getApplicationContext(),
                            "Nome da Medicação, quantidade e horário são obrigatórios!",
                            Toast.LENGTH_SHORT).show();

                }else {
                    // Pegando os dados na interface
                    String nome = textInputEditTextNomeMedicacao.getText().toString();
                    String tipo = textInputEditTextTipo.getText().toString();
                    String quantidade = textInputEditTextQuantidade.getText().toString();
                    String horarioString = textInputEditTextHorario.getText().toString();
                    String duracao = textInputEditTextDuracao.getText().toString();

                    // Montando SQL para inserir dados
                    String cmd = "INSERT INTO medicacoes (nome, tipo, quantidade, horario, duracao) VALUES ('";
                    cmd = cmd + nome;
                    cmd = cmd + "', '";
                    cmd = cmd + tipo;
                    cmd = cmd + "', '";
                    cmd = cmd + quantidade;
                    cmd = cmd + "', '";
                    cmd = cmd + horarioString;
                    cmd = cmd + "', '";
                    cmd = cmd + duracao;
                    cmd = cmd + "')";
                    // Executando comando
                    bd.execSQL( cmd );




                    LocalTime horario = getHoraByString(horarioString);

                    criarAlarme(horario.getHour(),horario.getMinute()); //Define o Horário do alarme de acordo com o da Medicação
                    //Toast.makeText(getApplicationContext(),"M nome" + medicacao.getNome(),Toast.LENGTH_LONG).show();
                    //setResult(RESULT_OK, i);

                    // Renovando o cursor do adapter, já que temos novos dados no bd
                    cursorMedicacoes = bd.rawQuery( "SELECT _rowid_ _id, id, nome, horario, quantidade FROM medicacoes", null );
                    adapterMedicacoes.changeCursor(cursorMedicacoes);
                    finish();
                }
            }
        });

        this.btnVoltarMedicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NovaMedicacaoActivity.class);
                setResult(RESULT_CANCELED,i);
                finish();

            }
        });

    }
    private boolean validaDados(){
        String nome = this.textInputEditTextNomeMedicacao.getText().toString();
        String quantidade = this.textInputEditTextQuantidade.getText().toString();
        String horario = this.textInputEditTextHorario.getText().toString();
        if(nome.isEmpty() || quantidade.isEmpty() || horario.isEmpty())
            return false;
        return true;
    }
    public void criarAlarme(int hora,int minuto){
        AlarmManager alarmMgr = (AlarmManager)getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmeReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, FLAG_IMMUTABLE);

        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hora); //Define a Hora
        calendar.set(Calendar.MINUTE,minuto); //Define o minuto
        // calendar.add(Calendar.SECOND,20); //Apenas para testes
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent); //Repete o Alarme diariamente
       // Toast.makeText(getApplicationContext(),"Criamos um alarme para essa medicação!", Toast.LENGTH_LONG);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalTime getHoraByString(String str){
        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("H:mm");
        return LocalTime.parse(str,formatadorHora);

    }




}