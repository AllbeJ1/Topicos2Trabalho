package br.com.notify.med;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlarmeMedicacaoActivity extends AppCompatActivity {
    private TextView txtNomeMedicacao, txtQuantidade;
    private Button btnMedicamentoConsumido;
    private Medicacao medicacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarme_medicacao);
        this.btnMedicamentoConsumido = findViewById(R.id.btnMedicamentoConsumido);
        this.txtNomeMedicacao = findViewById(R.id.txtNomeMedicamento);
        this.txtQuantidade = findViewById(R.id.txtQuantidade);
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

    public Medicacao buscaMedicacaoHorarioAtual(){
        //Realiza a busca no banco de dados pela medicação correspondente ao horário atual
        return null;
    }
}