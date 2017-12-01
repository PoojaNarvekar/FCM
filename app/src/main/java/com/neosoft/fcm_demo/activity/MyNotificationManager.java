package com.neosoft.fcm_demo.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.neosoft.fcm_demo.R;

/**
 * Created by webwerks1 on 1/12/17.
 */

public class MyNotificationManager {
    private Context mContext;
    public static final int NOTIFICATION_ID = 123;


    public MyNotificationManager(Context context) {
        this.mContext = context;
    }

    public void showNotification(String from, String notification, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        Notification mNotification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(notification)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .build();


        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager =(NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,mNotification);


    }
}
