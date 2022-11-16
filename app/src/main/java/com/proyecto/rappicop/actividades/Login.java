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
import com.proyecto.rappicop.modelos.Usuario;

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
        String[] campos = {"usuario", "contraseña", "nombre", "correo", "rol"};

        try {
            Cursor cursor = db.query(rapidb.getTableName(), campos, "usuario" + "=? AND " + "contraseña" + "=?", parametros, null, null, null);
            cursor.moveToFirst();
            if (cursor.getString(1).equals(contrasena.getText().toString())) {
                Usuario usuario = new Usuario(cursor.getString(0), cursor.getString(2), cursor.getString(3), cursor.getString(4));

                Intent siguiente;
                switch (usuario.getRol()) {
                    case "Comprador":
                    default:
                        siguiente = new Intent(this, MainActivity.class);
                        break;
                    case "Vendedor":
                        siguiente = new Intent(this, Vendedor.class);
                        break;
                    case "domicilario":
                        siguiente = new Intent(this, Domilciliario.class);
                        break;
                }

                siguiente.putExtra("user", usuario);
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