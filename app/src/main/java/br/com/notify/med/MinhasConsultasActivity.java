package br.com.notify.med;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MinhasConsultasActivity extends AppCompatActivity {
    private Button btnAdicionarConsulta, btnVoltar;
    private ListView listaConsultas;
    private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
    private AdapterConsulta adapter;

    // Cursor com os dados recuperados do BD
    Cursor cursorConsultas;
    // Referência para o banco de dados
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_consultas);
        this.btnAdicionarConsulta = findViewById(R.id.btnAdicionarConsulta);
        this.btnVoltar = findViewById(R.id.btnVoltar);
        this.listaConsultas = findViewById(R.id.listaConsultas);

        // Abrindo ou criando o banco de dados
        bd = openOrCreateDatabase( "safetymed_bd", MODE_PRIVATE, null );
        // String para comandos SQL
        String cmd;
        // Criar a tabela artistas, se a mesma não existir
        cmd = "CREATE TABLE IF NOT EXISTS consultas (";
        cmd = cmd + "id INTEGER PRIMARY KEY AUTOINCREMENT, nomeMedico VARCHAR, motivoConsulta VARCHAR, lembrete VARCHAR, localizacao VARCHAR, data VARCHAR, horario VARCHAR)";
        bd.execSQL( cmd );

        // Criando cursor com os dados vindos do banco
        cursorConsultas = bd.rawQuery( "SELECT _rowid_ _id, id, nomeMedico, data, horario FROM consultas", null );

        this.adapter  = new AdapterConsulta(this,cursorConsultas);
        listaConsultas.setAdapter(adapter);

        btnAdicionarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NovaConsultaActivity.class);
                startActivityForResult(i,1);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                /*Intent i = new Intent(getApplicationContext(),NovaConsultaActivity.class);
                setResult(RESULT_CANCELED,i);
                finish();*/
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent i) {
        super.onActivityResult(requestCode, resultCode, i);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Consulta consulta = null;
                consulta = (Consulta) i.getSerializableExtra("consulta");
                consultas.add(consulta);
                adapter.notifyDataSetChanged();

                //Toast.makeText(getApplicationContext(),"Chegou OK " + consulta.getNomeMedico() , Toast.LENGTH_LONG).show(); //PAra testes apenas

            }else{
                Toast.makeText(getApplicationContext(),"Nenhuma consulta adicionada.", Toast.LENGTH_SHORT).show();
            }
        }
    }


}