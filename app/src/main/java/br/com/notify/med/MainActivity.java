package br.com.notify.med;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button btnVerconsultas;
    private Button btnVerMedicacoes;
    private Button btnSobre;
    private Button btnSair;

    //Autenticacao
    private FirebaseAuth usuarios = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnVerconsultas = findViewById(R.id.btnVerConsultas);
        this.btnVerMedicacoes = findViewById(R.id.btnVerMedicacoes);
        this.btnSobre = findViewById(R.id.btnSobre);
        this.btnSair = findViewById(R.id.btnSair);


        btnVerconsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MinhasConsultasActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        btnVerMedicacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MinhasMedicacoesActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Sobre.class);
                view.getContext().startActivity(intent);

            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
       
    }

}