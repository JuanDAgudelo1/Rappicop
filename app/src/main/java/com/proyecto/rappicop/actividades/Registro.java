package com.proyecto.rappicop.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.proyecto.rappicop.MainActivity;
import com.proyecto.rappicop.R;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void iniciar_sesion(View view) {
        startActivity(new Intent(Registro.this, Login.class));
    }

    public void mainActivity(View view) {
        startActivity(new Intent(Registro.this, MainActivity.class));

    }
}