package com.proyecto.rappicop.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.proyecto.rappicop.MainActivity;
import com.proyecto.rappicop.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void registrese(View view) {
        startActivity(new Intent(Login.this, Registro.class));
    }

    public void mainActivity(View view) {
        startActivity(new Intent(Login.this, MainActivity.class));

    }
}