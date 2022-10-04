package com.proyecto.rappicop.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.DB.Utilidades;
import com.proyecto.rappicop.MainActivity;
import com.proyecto.rappicop.R;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private EditText campoUsuarioLogin;
    private EditText campoContrasenaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseHelper conexion = new DatabaseHelper(this,"bd_usuarios", null,1);

        campoUsuarioLogin = (EditText) findViewById(R.id.editText2);
        campoContrasenaLogin = (EditText) findViewById(R.id.textview3);
    }

    public void registrese(View view) {
        startActivity(new Intent(Login.this, Registro.class));
    }

    public void mainActivity(View view) {
        startActivity(new Intent(Login.this, MainActivity.class));


    }
    public void onClick(View view){


        switch (view.getId()){
            case R.id.button:
                login(campoContrasenaLogin);
                viewAll();
                break;
            case R.id.registro:
                Intent siguiente = new Intent(this, Registro.class);
                startActivity(siguiente);
                break;
        }
    }

    public void login(EditText contrasena){


        DatabaseHelper conexion = new DatabaseHelper(this,"bd_usuarios", null,5);

        SQLiteDatabase db = conexion.getReadableDatabase();

        String[] parametros = {campoUsuarioLogin.getText().toString()};

        String[] campos = {Utilidades.CAMPO_USUARIOREGISTRADO,Utilidades.CAMPO_CONTRASENA};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, Utilidades.CAMPO_USUARIOREGISTRADO + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            if(cursor.getString(1).equals(contrasena.getText().toString())){
                Intent siguiente = new Intent(this, MainActivity.class);
                System.out.println(contrasena.getText());
                startActivity(siguiente);
            }else{
                Toast.makeText(getApplicationContext(),"Contraseña incorrecta", Toast.LENGTH_LONG).show();
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_LONG).show();
        }
    }

    public void viewAll(){


        DatabaseHelper conexion = new DatabaseHelper(this,"bd_usuarios", null,1);

        SQLiteDatabase db = conexion.getReadableDatabase();

        String[] campos = {Utilidades.CAMPO_NOMBRECOMPLETO,Utilidades.CAMPO_CORREO,Utilidades.CAMPO_USUARIOREGISTRADO, Utilidades.CAMPO_CONTRASENA, Utilidades.CAMPO_CONFIRMARCONTRASENA};

        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, campos, null, null, null, null, null);
            if(cursor.moveToFirst()){
                do {
                    System.out.println(cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" " +cursor.getString(3)+" "+cursor.getString(4));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El usuario no existe", Toast.LENGTH_LONG).show();
        }
    }
}