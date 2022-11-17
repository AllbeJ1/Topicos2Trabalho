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

public class MinhasMedicacoesActivity extends AppCompatActivity {
    private Button btnAdicionarMedicacao, btnVoltar;
    private ListView listaMedicacoes;
    private ArrayList<Medicacao >medicacoes = new ArrayList<Medicacao>();
    private AdapterMedicacao adapter;

    // Cursor com os dados recuperados do BD
    Cursor cursorMedicacoes;
    // Referência para o banco de dados
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_medicacoes);
        this.btnAdicionarMedicacao = findViewById(R.id.btnAdicionarMedicacao);
        this.btnVoltar = findViewById(R.id.btnVoltar);
        this.listaMedicacoes = findViewById(R.id.listaMedicacoes);

        // Abrindo ou criando o banco de dados
        bd = openOrCreateDatabase( "safetymed_bd", MODE_PRIVATE, null );
        // String para comandos SQL
        String cmd;
        // Criar a tabela artistas, se a mesma não existir
        cmd = "CREATE TABLE IF NOT EXISTS medicacoes (";
        cmd = cmd + "id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, tipo VARCHAR, quantidade VARCHAR, horario VARCHAR, duracao VARCHAR)";
        bd.execSQL( cmd );

        // Criando cursor com os dados vindos do banco
        cursorMedicacoes = bd.rawQuery( "SELECT _rowid_ _id, id, nome, horario, quantidade FROM medicacoes", null );

        this.adapter  = new AdapterMedicacao(this,cursorMedicacoes/*options*/);
        listaMedicacoes.setAdapter(adapter);



        btnAdicionarMedicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),NovaMedicacaoActivity.class);
                startActivityForResult(i,1);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }




}