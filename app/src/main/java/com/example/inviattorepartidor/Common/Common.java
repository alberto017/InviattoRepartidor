package com.example.inviattorepartidor.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.inviattorepartidor.Model.RepartidorModel;

public class Common {
    public static RepartidorModel currentRepartidorModel;
    private static final String BASE_URL = "https://fcm.googleapis.com/";
    /*
    public static IAPIService getFCMService(){
        return RetrofitClient.getClient(BASE_URL).create(IAPIService.class);
    }//getFCMService
     */
    public static String topicNews = "News";
    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Password";

    public static String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Enviada";
        else if (status.equals("1"))
            return "En camino";
        else
            return "Enviada";
    }//convertCodeToStatus

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }//if
                }//for
            }//if
        }//if
        return false;
    }//isConnectedToInternet
}//Common
