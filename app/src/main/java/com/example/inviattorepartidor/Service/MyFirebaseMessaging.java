package com.example.inviattorepartidor.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import com.example.inviattorepartidor.Common.Common;
import com.example.inviattorepartidor.Helper.NotificationHelper;
import com.example.inviattorepartidor.MenuLateral;
import com.example.inviattorepartidor.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData() != null){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                sendNotificationAPI26(remoteMessage);
            }else{
                sendNotification(remoteMessage);
            }//else
        }
    }//onMessageReceived

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendNotificationAPI26(RemoteMessage remoteMessage) {
        Map<String,String> notification = remoteMessage.getData();
        String title = notification.get("title");
        String content = notification.get("message");

        PendingIntent pendingIntent;
        NotificationHelper helper;
        Notification.Builder builder;

        if(Common.currentRepartidorModel != null){
            Intent intent = new Intent(this, MenuLateral.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivity(getApplication(),0,intent,PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            helper = new NotificationHelper(this);
            builder = helper.getInviattoChannelNotification(title,content,pendingIntent,defaultSoundUri);
            helper.getManager().notify(new Random().nextInt(),builder.build());
        }//if

    }//sendNotificationAPI26

    private void sendNotification(RemoteMessage remoteMessage) {
        Map<String,String> notification = remoteMessage.getData();
        String title = notification.get("title");
        String content = notification.get("message");

        if(Common.currentRepartidorModel != null){
            //RemoteMessage.Notification notification = remoteMessage.getNotification();
            Intent intent = new Intent(this, MenuLateral.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplication(),0,intent,PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentTitle(content)
                    //.setContentText(notification.getBody())
                    //.setContentText(notification.getBody())
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,builder.build());
        }else{
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentTitle(content)
                    //.setContentText(notification.getBody())
                    //.setContentText(notification.getBody())
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri);
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,builder.build());

        }
    }//sendNotification

}//MyFirebaseMessaging
