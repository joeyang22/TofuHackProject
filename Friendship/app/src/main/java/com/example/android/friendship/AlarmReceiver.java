package com.example.android.friendship;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by joe on 15-02-01.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context k1, Intent k2) {
        // TODO Auto-generated method stub
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(k1)
                            .setSmallIcon(R.drawable.ic_logo)
                            .setContentTitle("Missed Task!")
                            .setContentText("Check your feed for details!")
                            .setSound(alarmSound);
            int mNotificationId = 001;


            NotificationManager mNotifyMgr =
                    (NotificationManager) k1.getSystemService(Context.NOTIFICATION_SERVICE);
// Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }


}