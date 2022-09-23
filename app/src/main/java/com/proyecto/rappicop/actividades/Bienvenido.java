package com.proyecto.rappicop.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.proyecto.rappicop.R;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bienvenido);
    }

    public void registrese(View view) {
        startActivity(new Intent(Bienvenido.this, Registro.class));
    }

    public void iniciar_sesion(View view) {
        startActivity(new Intent(Bienvenido.this,Registro.class));

    }
}