package br.com.notify.med;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;

import java.util.ArrayList;

public class AdapterMedicacao extends ArrayAdapter<Medicacao>  /*FirebaseListAdapter<Medicacao>*/ {
    private Context context;
    private ArrayList<Medicacao> medicacoes;

    //Banco de Dados
    private DatabaseReference BD = FirebaseDatabase.getInstance().getReference();

    public AdapterMedicacao(Context context, ArrayList<Medicacao> medicacoes/*FirebaseListOptions options*/) {
        //super(options);
        super(context, R.layout.item_lista_medicacao, medicacoes);
        this.context = context;
        this.medicacoes = medicacoes;
    }

    /*@Override
    protected void populateView(View v, Medicacao medicacao, int position) {

        TextView lblNomeMedicacao = v.findViewById(R.id.lblNomeMedicacao);
        TextView lblHorarioMedicacao = v.findViewById(R.id.lblHorarioMedicacao);
        TextView lblQuantidadeMedicacao = v.findViewById(R.id.lblQuantidadeMedicacao);

        lblNomeMedicacao.setText(this.medicacoes.get(position).getNome());
        lblHorarioMedicacao.setText(this.medicacoes.get(position).getHorario());
        lblQuantidadeMedicacao.setText( this.medicacoes.get(position).getQuantidade());
    }*/


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera um objeto "inflador" de layouts
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        // "Inflando" o XML do item da lista, gerando sua visualização (view)
        View itemView = li.inflate(R.layout.item_lista_medicacao, parent, false);

        TextView lblNomeMedicacao = itemView.findViewById(R.id.lblNomeMedicacao);
        TextView lblHorarioMedicacao = itemView.findViewById(R.id.lblHorarioMedicacao);
        TextView lblQuantidadeMedicacao = itemView.findViewById(R.id.lblQuantidadeMedicacao);



        lblNomeMedicacao.setText(this.medicacoes.get(position).getNome());
        lblHorarioMedicacao.setText(this.medicacoes.get(position).getHorario().toString());
        lblQuantidadeMedicacao.setText( this.medicacoes.get(position).getQuantidade());


        //DatabaseReference dados = BD.child("dados");
        //dados.addValueEventListener(new EscutadorFirebase());

        return itemView;
    }

    /*private class EscutadorFirebase implements ValueEventListener {
        // O método onDataChange é chamado em 2 ocasiões:
        // - assim que o Escutador é criado e associado ao respectivo nó;
        // - sempre que houver alguma alteração nos dados.
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // Os dados recuperados no Firebase vem dentro de um DataSnapshot.
            // No nosso caso, só vai ter um objeto lá dentro (objeto da classe Usuario).
            // Precisamos testar se veio alguma informação, isto é, se o dataSnapshot existe...
            if ( dataSnapshot.exists()) {
                // Recuperamos o objeto Usuario que veio dentro do dataSnapshot:
                Medicacao medicacao = dataSnapshot.getValue(Medicacao.class);
                // Colocando os dados do objeto lido na interface gráfica.
                lblNomeMedicacao.setText(medicacao.getNome());
                lblHorarioMedicacao.setText(medicacao.getHorario());
                lblQuantidadeMedicacao.setText(medicacao.getQuantidade());
            }
        }

        // Não trabalharemos com este método. Mas ele tem que existir!!
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) { }
    }*/
}
