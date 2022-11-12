package br.com.notify.med;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdapterConsulta  extends ArrayAdapter<Consulta> {
    private Context context;
    private ArrayList<Consulta> consultas;

    public AdapterConsulta(Context context, ArrayList<Consulta> consultas) {
        super(context, R.layout.item_lista_consulta, consultas);
        this.context = context;
        this.consultas = consultas;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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
    }
}
