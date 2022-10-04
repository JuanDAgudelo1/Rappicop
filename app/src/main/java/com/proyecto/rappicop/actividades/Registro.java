package com.proyecto.rappicop.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.DB.Utilidades;
import com.proyecto.rappicop.MainActivity;
import com.proyecto.rappicop.R;

public class Registro extends AppCompatActivity {

    private EditText campoNombreCompleto;
    private EditText campoUsuarioRegistado;
    private EditText campoCorreo;
    private EditText campoContrasena;
    private EditText campoConfirmarContrasena;

    private RadioButton campoMasculino;
    private RadioButton campoFemenino;
    private RadioButton campoOtro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoNombreCompleto = (EditText) findViewById(R.id.nombre);
        campoCorreo = (EditText) findViewById(R.id.correo);
        campoUsuarioRegistado = (EditText) findViewById(R.id.usuario);
        campoContrasena = (EditText) findViewById(R.id.contraseña);
        campoConfirmarContrasena = (EditText) findViewById(R.id.conficontraseña);

        campoMasculino = (RadioButton) findViewById(R.id.radioButton_male);
        campoFemenino = (RadioButton) findViewById(R.id.radioButton_female);
        campoOtro = (RadioButton) findViewById(R.id.radioButton_otro);

    }

    public void iniciar_sesion(View view) {
        startActivity(new Intent(Registro.this, Login.class));
    }

    public void mainActivity(View view) {
        startActivity(new Intent(Registro.this, MainActivity.class));

    }


    public void envioDatos(View view) {

        registrarUsuarios();
    }


    public void registrarUsuarios() {


        String campoSexo = "";

        DatabaseHelper conexion = new DatabaseHelper(this, "bd_usuarios", null, 5);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues values = new ContentValues();

        if (campoMasculino.isChecked()) {
            campoSexo = "Masculino";
        }
        if (campoFemenino.isChecked()) {
            campoSexo = "Femenino";
        }
        if (campoOtro.isChecked()) {
            campoSexo = "Otro";
        }


        if(campoContrasena.getText().toString().equals(campoConfirmarContrasena.getText().toString())) {
            values.put(Utilidades.CAMPO_NOMBRECOMPLETO, campoNombreCompleto.getText().toString());
            values.put(Utilidades.CAMPO_USUARIOREGISTRADO, campoUsuarioRegistado.getText().toString());
            values.put(Utilidades.CAMPO_CORREO, campoCorreo.getText().toString());
            values.put(Utilidades.CAMPO_CONTRASENA, campoContrasena.getText().toString());
            values.put(Utilidades.CAMPO_CONFIRMARCONTRASENA, campoConfirmarContrasena.getText().toString());

            values.put(Utilidades.CAMPO_SEXO, campoSexo);

            Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_NOMBRECOMPLETO, values);

            Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();

            db.close();
        }else{
            Toast.makeText(getApplicationContext(),"La contraseña no es igual", Toast.LENGTH_LONG).show();
        }
    }

}