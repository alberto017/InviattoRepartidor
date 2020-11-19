package com.example.inviattorepartidor.Service;

import com.example.inviattorepartidor.Common.Common;
import com.example.inviattorepartidor.Model.Token;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

import androidx.annotation.NonNull;

public class MyFirebaseIdService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        //String tokenRegfreshed = FirebaseInstanceId.getInstance().getToken();
        //String tokenRegfreshed = FirebaseInstanceId.getInstance().getInstanceId().getResult().getToken();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        updateTokenToFirebase(refreshToken);
    }//onNewToken

    public void updateTokenToFirebase(String refreshToken) {
        if(Common.currentRepartidorModel != null){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference tokens = database.getReference("Tokens");
            Token data = new Token(refreshToken,true);
            tokens.child(Common.currentRepartidorModel.getPhone()).setValue(data);
        }//if


    }//updateTokenToFirebase


}//MyFirebaseService
