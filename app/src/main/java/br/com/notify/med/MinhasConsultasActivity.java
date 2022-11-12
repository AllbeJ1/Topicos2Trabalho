package br.com.notify.med;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MinhasConsultasActivity extends AppCompatActivity {
    private Button btnAdicionarConsulta, btnVoltar;
    private ListView listaConsultas;
    private ArrayList<Consulta> consultas = new ArrayList<Consulta>();
    private AdapterConsulta adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_consultas);
        this.btnAdicionarConsulta = findViewById(R.id.btnAdicionarConsulta);
        this.btnVoltar = findViewById(R.id.btnVoltar);
        this.listaConsultas = findViewById(R.id.listaConsultas);
        this.adapter  = new AdapterConsulta(this,consultas);
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
                Toast.makeText(getApplicationContext(),"Nenhuma consulta adicionada. Result = " + resultCode, Toast.LENGTH_SHORT).show();
            }
        }
    }

}