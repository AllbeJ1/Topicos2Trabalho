package br.com.notify.med;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class NovaMedicacaoActivity extends AppCompatActivity {
    private Button btnAddMedicacao, btnVoltarMedicacao;

    private TextInputEditText textInputEditTextNomeMedicacao, textInputEditTextTipo, textInputEditTextQuantidade,
            textInputEditTextHorario, textInputEditTextDuracao;

    //Banco de Dados
    private DatabaseReference BD = FirebaseDatabase.getInstance().getReference();

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

        this.btnAddMedicacao.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(!validaDados()){
                    Toast.makeText(getApplicationContext(),
                            "Nome da Medicação, quantidade e horário são obrigatórios!",
                            Toast.LENGTH_SHORT).show();

                }else {
                    String nome = textInputEditTextNomeMedicacao.getText().toString();
                    String tipo = textInputEditTextTipo.getText().toString();
                    String quantidade = textInputEditTextQuantidade.getText().toString();
                    String horario = textInputEditTextHorario.getText().toString();
                    String duracao = textInputEditTextDuracao.getText().toString();

                    Intent i = new Intent(getApplicationContext(), NovaMedicacaoActivity.class);
                    Medicacao medicacao = new Medicacao(nome, tipo, quantidade, horario, duracao);
                    i.putExtra("medicacao", medicacao);
                    //As entradas (principalmente horario) deverão ser tratadas, esses dados serão salvos no Banco de Dados
                    criarAlarme(21,27); //
                    //Toast.makeText(getApplicationContext(),"M nome" + medicacao.getNome(),Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK, i);
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
        PendingIntent alarmIntent = PendingIntent.getBroadcast(NovaMedicacaoActivity.this, 0, intent, 0);

        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        //calendar.set(Calendar.HOUR_OF_DAY, hora);
        //calendar.set(Calendar.MINUTE,minuto);
        calendar.add(Calendar.SECOND,10); //Apenas para testes
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);

    }



}