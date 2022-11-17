package br.com.notify.med;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cursoradapter.widget.CursorAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterMedicacao extends CursorAdapter {
    private Context context;
    private ArrayList<Medicacao> medicacoes;

    public AdapterMedicacao(Context context, Cursor cursor ){
        super(context, cursor, 0);
       
        this.context = context;
        //this.medicacoes = medicacoes;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Recupera o objeto inflador
        LayoutInflater inflater = LayoutInflater.from(context);
        // Infla o xml, gerando a visualização (view)
        View v = inflater.inflate(R.layout.item_lista_medicacao, parent, false);
        // Retorna o objeto inflado (a view gerada)
        return v;
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View v, Context context, Cursor cursor) {
        // Recuperando os objetos gráficos de dentro da view recebid
        TextView lblNomeMedicacao = v.findViewById(R.id.lblNomeMedicacao);
        TextView lblHorarioMedicacao = v.findViewById(R.id.lblHorarioMedicacao);
        TextView lblQuantidadeMedicacao = v.findViewById(R.id.lblQuantidadeMedicacao);

        // O cursor já vem posicionado na linha correta...
        // Basta recuperarmos os dados
        // Finalmente, colocamos os dados nos objetos gráficos que estão dentro do item da lista
        lblNomeMedicacao.setText(cursor.getString(cursor.getColumnIndex("nome")));
        lblHorarioMedicacao.setText(cursor.getString(cursor.getColumnIndex("horario")));
        lblQuantidadeMedicacao.setText(cursor.getString(cursor.getColumnIndex("quantidade")));
    }


}
