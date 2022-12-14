package com.proyecto.rappicop.vendedor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Usuario;

public class Vendedor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);

        Button crearOferta = findViewById(R.id.crearOferta);
        Button verOfertas = findViewById(R.id.verOfertas);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        crearOferta.setOnClickListener(view -> {
            Intent i = new Intent(this, OfertarAlimento.class);
            i.putExtra("user", user);
            startActivity(i);
        });

        verOfertas.setOnClickListener(view -> {
            Intent i = new Intent(this, ListarOfertasPorVendedor.class);
            i.putExtra("user", user);
            startActivity(i);
        });
    }
}