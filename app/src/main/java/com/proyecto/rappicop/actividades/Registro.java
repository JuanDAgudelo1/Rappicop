package com.proyecto.rappicop.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;

public class Registro extends AppCompatActivity {

    Button crear;
    EditText usuario, nombre, correo, contrasena, conficontra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        crear = findViewById(R.id.button);

        usuario = findViewById(R.id.usuario);
        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contraseña);
        conficontra = findViewById(R.id.conficontraseña);

        crear.setOnClickListener(view -> {
            String Usuario = usuario.getText().toString();
            String Nombres = nombre.getText().toString();
            String Correo = correo.getText().toString();
            String Contrasena = contrasena.getText().toString();
            String ConfirmarContrasena = conficontra.getText().toString();

            Logica crearusuario = new Logica(Registro.this);
            long id = crearusuario.nuevoUsuario(Usuario, Nombres, Correo, Contrasena, ConfirmarContrasena);

            if (id > 0) {
                Toast.makeText(Registro.this, "Registro guardado", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Registro.this, Login.class);
                startActivity(i);
            } else {
                Toast.makeText(Registro.this, "Registro no guardado", Toast.LENGTH_LONG).show();
            }
        });
    }
}