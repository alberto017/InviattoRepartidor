package com.example.inviattorepartidor.Helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import com.example.inviattorepartidor.R;

import androidx.annotation.RequiresApi;

public class NotificationHelper extends ContextWrapper {

    private static final String CHANNEL_ID = "com.example.sanchez.eatit.INVIATTO";
    private static final String CHANNEL_NAME = "Inviatto";
    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannel();
    }//NotificationHelper

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(){
        NotificationChannel inviattoChannel = new NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        inviattoChannel.enableLights(false);
        inviattoChannel.enableLights(true);
        inviattoChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(inviattoChannel);
    }//createChannel

    public NotificationManager getManager() {
        if(manager == null)
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        return manager;
    }//getManager

    @RequiresApi(api = Build.VERSION_CODES.O)
    public android.app.Notification.Builder getInviattoChannelNotification(String title, String body, PendingIntent contentIntent, Uri soundUri){
        return new android.app.Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentIntent(contentIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setAutoCancel(false);
    }//getInviattoChannelNotification

}//NotificationHelper
