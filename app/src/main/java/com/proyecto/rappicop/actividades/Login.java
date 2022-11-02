package com.proyecto.rappicop.actividades;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.MainActivity;
import com.proyecto.rappicop.R;

public class Login extends AppCompatActivity {

    private EditText campoUsuarioLogin;
    private EditText campoContrasenaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoUsuarioLogin = findViewById(R.id.editText2);
        campoContrasenaLogin = findViewById(R.id.textview3);
    }

    public void onClickRegister(View view) {
        startActivity(new Intent(Login.this, Registro.class));
    }

    public void onClickLogin(View view) {
        login(campoContrasenaLogin);
    }

    public void login(EditText contrasena) {
        DatabaseHelper rapidb = new DatabaseHelper(Login.this);

        SQLiteDatabase db = rapidb.getReadableDatabase();

        String[] parametros = {campoUsuarioLogin.getText().toString(), campoContrasenaLogin.getText().toString()};
        String[] campos = {"usuario", "contraseña"};

        try {
            Cursor cursor = db.query(rapidb.getTableName(), campos, "usuario" + "=? AND " + "contraseña" + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            if (cursor.getString(1).equals(contrasena.getText().toString())) {
                Intent siguiente = new Intent(this, MainActivity.class);
                siguiente.putExtra("mesagge", campoUsuarioLogin.getText().toString());
                startActivity(siguiente);
            } else {
                Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El usuario no existe", Toast.LENGTH_LONG).show();
        }
    }
}