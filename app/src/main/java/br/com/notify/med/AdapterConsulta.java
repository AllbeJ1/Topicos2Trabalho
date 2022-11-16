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

public class AdapterConsulta  extends CursorAdapter/*ArrayAdapter<Consulta>*/ {
    private Context context;
    private ArrayList<Consulta> consultas;

    public AdapterConsulta(Context context, Cursor cursor /*ArrayList<Consulta> consultas*/) {
        super(context, cursor, 0);
        //super(context, R.layout.item_lista_consulta, consultas);
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

    /*@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera um objeto "inflador" de layouts
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        // "Inflando" o XML do item da lista, gerando sua visualização (view)
        View itemView = li.inflate(R.layout.item_lista_consulta, parent, false);

        TextView lblNomeMedico = itemView.findViewById(R.id.lblNomeMedico);
        TextView lbldata = itemView.findViewById(R.id.lblData);
        TextView lblHora = itemView.findViewById(R.id.lblHora);

        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");

        String dataFormatada = formatadorData.format(this.consultas.get(position).getData());
        String horaFormatada = formatadorHora.format(this.consultas.get(position).getHorario());

        lblNomeMedico.setText("Dr. " + this.consultas.get(position).getNomeMedico());
        lbldata.setText(dataFormatada);
        lblHora.setText( horaFormatada);


        return itemView;
    }*/
}
