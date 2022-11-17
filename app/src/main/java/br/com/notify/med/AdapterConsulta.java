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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdapterConsulta  extends CursorAdapter {
    private Context context;
   // private ArrayList<Consulta> consultas;

    public AdapterConsulta(Context context, Cursor cursor) {
        super(context, cursor, 0);

        this.context = context;
        //this.consultas = consultas;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Recupera o objeto inflador
        LayoutInflater inflater = LayoutInflater.from(context);
        // Infla o xml, gerando a visualização (view)
        View v = inflater.inflate(R.layout.item_lista_consulta, parent, false);
        // Retorna o objeto inflado (a view gerada)
        return v;
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View v, Context context, Cursor cursor) {
        // Recuperando os objetos gráficos de dentro da view recebid
        TextView lblNomeMedico = v.findViewById(R.id.lblNomeMedico);
        TextView lbldata = v.findViewById(R.id.lblData);
        TextView lblHora = v.findViewById(R.id.lblHora);

        // O cursor já vem posicionado na linha correta...
        // Basta recuperarmos os dados
        // Finalmente, colocamos os dados nos objetos gráficos que estão dentro do item da lista
        lblNomeMedico.setText(cursor.getString(cursor.getColumnIndex("nomeMedico")));
        lbldata.setText(cursor.getString(cursor.getColumnIndex("data")));
        lblHora.setText(cursor.getString(cursor.getColumnIndex("horario")));
    }


}
