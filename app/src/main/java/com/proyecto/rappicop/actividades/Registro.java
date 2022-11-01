package com.proyecto.rappicop.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;

public class Registro extends AppCompatActivity {

    Button crear;
    EditText usuario, nombre, correo,contrasena, conficontra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        crear = (Button) findViewById(R.id.button);

        usuario = (EditText) findViewById(R.id.usuario);
        nombre = (EditText) findViewById(R.id.nombre);
        correo = (EditText) findViewById(R.id.correo);
        contrasena = (EditText) findViewById(R.id.contraseña);
        conficontra = (EditText) findViewById(R.id.conficontraseña);



        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Usuario = usuario.getText().toString();
                String Nombres = nombre.getText().toString();
                String Conficontraseña = conficontra.getText().toString();
                String Correo = correo.getText().toString();
                String Contrasena = contrasena.getText().toString();

                Logica crearusuario = new Logica(Registro.this);
                long id = crearusuario.nuevoUsuario(Usuario,Nombres,Conficontraseña,Correo,Contrasena);

                if(id > 0){
                    Toast.makeText(Registro.this, "Registro guardado", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Registro.this, Login.class);
                    startActivity(i);
                }else {
                    Toast.makeText(Registro.this, "Registro no guardado", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}