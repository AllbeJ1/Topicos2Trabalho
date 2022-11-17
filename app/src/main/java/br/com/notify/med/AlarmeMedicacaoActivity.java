package br.com.notify.med;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalTime;

public class AlarmeMedicacaoActivity extends AppCompatActivity {
    private TextView txtNomeMedicacao, txtQuantidade;
    private Button btnMedicamentoConsumido;
    private Medicacao medicacao;
    private SQLiteDatabase bd;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarme_medicacao);
        this.btnMedicamentoConsumido = findViewById(R.id.btnMedicamentoConsumido);
        this.txtNomeMedicacao = findViewById(R.id.txtNomeMedicamento);
        this.txtQuantidade = findViewById(R.id.txtQuantidade);
        bd = openOrCreateDatabase( "safetymed_bd", MODE_PRIVATE, null );

        this.medicacao= buscaMedicacaoHorarioAtual();

        if(medicacao == null){
            Intent MinhasMedicacoes = new Intent(this, MinhasMedicacoesActivity.class);
            startActivity(MinhasMedicacoes);
        }else{
            txtNomeMedicacao.setText(medicacao.getNome());
            txtQuantidade.setText(medicacao.getQuantidade());
        }

        this.btnMedicamentoConsumido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("Range")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Medicacao buscaMedicacaoHorarioAtual(){
        //Realiza a busca no banco de dados pela medicação correspondente ao horário atual
        LocalTime horaRemedio = LocalTime.now();

        String cmd = "SELECT nome, quantidade FROM medicacao ";
        cmd = cmd + "WHERE horario LIKE %" + horaRemedio.getHour() + ":" + horaRemedio.getMinute() +"%" ;
        cmd = cmd + " LIMIT 1";


        Cursor remedioLido =  bd.rawQuery(cmd,null);
        while( remedioLido.moveToNext() ) {
            String nome = remedioLido.getString(remedioLido.getColumnIndex("nome"));
            String quantidade = remedioLido.getString(remedioLido.getColumnIndex("quantidade"));
            return new Medicacao(nome,quantidade);
        }
        return null;
    }
}