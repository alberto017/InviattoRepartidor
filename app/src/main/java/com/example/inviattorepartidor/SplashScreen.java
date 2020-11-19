package com.example.inviattorepartidor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DatabaseReference;

public class SplashScreen extends AppCompatActivity {


    //Declaracion de variables
    private DatabaseReference databaseReference = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Iniciamos Paper
        /*
        Paper.init(this);
         */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Acceso = new Intent(SplashScreen.this, Inicio.class);
                startActivity(Acceso);
                finish();
            }
        }, 2000);

        //Validar checkBox de guardado de sesion
        /*
        String user = Paper.book().read(Common.USER_KEY);
        String pwd = Paper.book().read(Common.PWD_KEY);
        if (user != null && pwd != null) {
            if (!user.isEmpty() && !pwd.isEmpty()) {
                login(user, pwd);
            }//if
        }//if
        */
    }//onCreate

    /*
    private void login(final String phone, final String pwd) {

        //Conexion a firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("User");

        if (Common.isConnectedToInternet(getBaseContext())) {
            final ProgressDialog progressDialog = new ProgressDialog(SplashScreen.this);
            progressDialog.setMessage("Espere un momento...");
            progressDialog.show();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Verificamos que exista el usiario posteriormente la contrasena
                    if (dataSnapshot.child(phone).exists()) {
                        progressDialog.dismiss();
                        UsuarioModel usuarioModel = dataSnapshot.child(phone).getValue(UsuarioModel.class);
                        if (usuarioModel.getPassword().equals(pwd)) {
                            if (usuarioModel.getSliderStatus().equals("activado")) {
                                Intent sliderWalkthrough = new Intent(SplashScreen.this, SliderWalkthrough.class);
                                Common.currentUsuarioModel = usuarioModel; //Obtenemos el usuario actual
                                startActivity(sliderWalkthrough);
                                finish();
                            } else {
                                Intent menuLateral = new Intent(SplashScreen.this, MenuLateral.class);
                                Common.currentUsuarioModel = usuarioModel; //Obtenemos el usuario actual
                                startActivity(menuLateral);
                                finish();
                            }//else
                        } else {
                            Toast.makeText(SplashScreen.this, "¡Contraseña incorrecta!", Toast.LENGTH_SHORT).show();
                        }//else
                    } else {
                        Toast.makeText(SplashScreen.this, "¡Usuario incorecto!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }//else
                }//onDataChange

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }//onCancelled
            });
        } else {
            Toast.makeText(SplashScreen.this, "¡Revisa tu Conexion a Internet!", Toast.LENGTH_LONG).show();
            return;
        }//else
    }//login
     */
}