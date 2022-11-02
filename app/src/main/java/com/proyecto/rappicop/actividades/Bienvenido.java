package com.proyecto.rappicop.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.R;

public class Bienvenido extends AppCompatActivity {

    Button btnIniciarSesion, btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bienvenido);

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnIniciarSesion.setOnClickListener(view -> {
            startActivity(new Intent(Bienvenido.this, Login.class));
        });

        btnRegistrarse.setOnClickListener(view -> {
            startActivity(new Intent(Bienvenido.this, Registro.class));
        });
    }
}