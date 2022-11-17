package br.com.notify.med;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {


        Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(4000); //Vibra por 4 segundos

        Toast.makeText(context, "Hora do remédio!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        //Usa o despertador/alarme do celular
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);

        // Toca o alarme
        ringtone.play();
        Intent telaAlarme = new Intent(context, AlarmeMedicacaoActivity.class);
        
        context.startActivity(telaAlarme);
    }

}