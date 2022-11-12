package br.com.notify.med;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MinhasMedicacoesActivity extends AppCompatActivity {
    private Button btnAdicionarMedicacao, btnVoltar;
    private ListView listaMedicacoes;
    private ArrayList<Medicacao >medicacoes = new ArrayList<Medicacao>();
    private AdapterMedicacao adapter;

    //Banco de Dados
    private DatabaseReference BD = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_medicacoes);
        this.btnAdicionarMedicacao = findViewById(R.id.btnAdicionarMedicacao);
        this.btnVoltar = findViewById(R.id.btnVoltar);
        this.listaMedicacoes = findViewById(R.id.listaMedicacoes);

        //Banco de Dados
        DatabaseReference dbMedicacoes = BD.child("medicacoes");

        /*FirebaseListOptions<Medicacao> options = new FirebaseListOptions.Builder<Medicacao>()
                .setLayout(R.layout.item_lista_medicacao)
                .setQuery(dbMedicacoes, Medicacao.class)
                .setLifecycleOwner(this)
                .build();*/
        this.adapter  = new AdapterMedicacao(this,medicacoes/*options*/);
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
                Medicacao medicacao = null;
                medicacao = (Medicacao) i.getSerializableExtra("medicacao");
                medicacoes.add(medicacao);
                adapter.notifyDataSetChanged();

                //Toast.makeText(getApplicationContext(),"Chegou OK " + consulta.getNomeMedico() , Toast.LENGTH_LONG).show(); //PAra testes apenas

            }else{
                Toast.makeText(getApplicationContext(),"Nenhuma Medicação adicionada", Toast.LENGTH_SHORT).show();
            }
        }
    }

}