package com.example.inviattorepartidor;

import androidx.appcompat.app.AppCompatActivity;
import info.hoang8f.widget.FButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Contacto extends AppCompatActivity {

    private FButton btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help = new Intent(Intent.ACTION_DIAL);
                help.setData(Uri.parse("tel:3222778890"));
                startActivity(help);
            }//onClick
        });
    }//onCreate

}//Contacto