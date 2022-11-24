package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Usuario;

public class AgregarDireccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_direccion);

        Logica iu = new Logica(AgregarDireccion.this);
        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        EditText nombre = findViewById(R.id.namedir);
        EditText dir = findViewById(R.id.ubi);
        Button agregar = findViewById(R.id.agregar);

        agregar.setOnClickListener(view -> {
            long di = iu.agregaUbi(user.getUsuario(), nombre.getText().toString(), dir.getText().toString());

            if (di > 0) {
                Toast.makeText(AgregarDireccion.this, "Agregado", Toast.LENGTH_LONG).show();
                onBackPressed();
            } else {
                Toast.makeText(AgregarDireccion.this, "No fue agregada", Toast.LENGTH_LONG).show();
            }
        });
    }
}
