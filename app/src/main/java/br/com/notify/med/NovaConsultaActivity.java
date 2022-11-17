package br.com.notify.med;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NovaConsultaActivity extends AppCompatActivity {
    private Button btnAddConsulta, btnVoltaConsulta;

    private TextInputEditText textInputEditTextNome, textInputEditTextMotivoConsulta, textInputEditTextData,
            textInputEditTextHora, textInputEditTextEndereco, textInputEditTextLembrete;

    // Adapter da lista de medicacoes
    AdapterConsulta adapterConsultas;
    // Cursor com os dados recuperados do BD
    Cursor cursorConsultas;
    // Referência para o banco de dados
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_consulta);
        this.textInputEditTextNome = findViewById(R.id.textInputEditTextNome);
        this.textInputEditTextMotivoConsulta = findViewById(R.id.textInputEditTextMotivoConsulta);
        this.textInputEditTextData = findViewById(R.id.textInputEditTextData);
        this.textInputEditTextHora = findViewById(R.id.textInputEditTextHora);
        this.textInputEditTextEndereco = findViewById(R.id.textInputEditTextEndereco);
        this.textInputEditTextLembrete = findViewById(R.id.textInputEditTextLembrete);
        this.btnAddConsulta = findViewById(R.id.btnAddConsulta);
        this.btnVoltaConsulta = findViewById(R.id.btnVoltaConsulta);

        // Abrindo ou criando o banco de dados
        bd = openOrCreateDatabase( "safetymed_bd", MODE_PRIVATE, null );

        btnAddConsulta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(!validaDadosConsulta())
                    Toast.makeText(getApplicationContext(),"Insira os dados primeiro!",Toast.LENGTH_LONG).show();
                else{
                    // Pegando os dados na interface
                    String nomeMedico = textInputEditTextNome.getText().toString();
                    String motivoConsulta = textInputEditTextMotivoConsulta.getText().toString();
                    String lembrete = textInputEditTextLembrete.getText().toString();
                    String endereco = textInputEditTextEndereco.getText().toString();
                    String dataString = textInputEditTextData.getText().toString();
                    String horaString = textInputEditTextHora.getText().toString();

                    // Montando SQL para inserir dados
                    String cmd = "INSERT INTO consultas (nomeMedico, motivoConsulta, lembrete, localizacao, data, horario) VALUES ('";
                    cmd = cmd + nomeMedico;
                    cmd = cmd + "', '";
                    cmd = cmd + motivoConsulta;
                    cmd = cmd + "', '";
                    cmd = cmd + lembrete;
                    cmd = cmd + "', '";
                    cmd = cmd + endereco;
                    cmd = cmd + "', '";
                    cmd = cmd + dataString;
                    cmd = cmd + "', '";
                    cmd = cmd + horaString;
                    cmd = cmd + "')";
                    // Executando comando
                    bd.execSQL( cmd );
                    // Renovando o cursor do adapter, já que temos novos dados no bd
                    cursorConsultas = bd.rawQuery( "SELECT _rowid_ _id, id, nome, data, horario FROM consultas", null );
                    adapterConsultas.changeCursor(cursorConsultas);

                    LocalDate data  = getDataByString(dataString);
                    LocalTime hora = getHoraByString(horaString);

                    Intent i = new Intent(getApplicationContext(),NovaConsultaActivity.class);
                    Consulta consulta = new Consulta(nomeMedico,motivoConsulta,lembrete,endereco,data,hora);
                    i.putExtra("consulta", consulta);

                    //Toast.makeText(getApplicationContext(),"M nome" + consulta.getNomeMedico(),Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK,i);
                    finish();

                }
            }
        });

        btnVoltaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NovaConsultaActivity.class);
                setResult(RESULT_CANCELED,i);
                finish();
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean validaDadosConsulta(){
        String nome = textInputEditTextNome.getText().toString();
        try{
            LocalDate data  = getDataByString(textInputEditTextData.getText().toString());
            LocalTime hora = getHoraByString(textInputEditTextHora.getText().toString());
            if(nome.isEmpty() || data == null || hora == null )
                return false;
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Data ou Hora em formatos incorretos!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalTime getHoraByString(String str){
        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("H:mm");
        return LocalTime.parse(str,formatadorHora);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate getDataByString(String str){
        DateTimeFormatter formatadorDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(str,formatadorDia);
    }
}