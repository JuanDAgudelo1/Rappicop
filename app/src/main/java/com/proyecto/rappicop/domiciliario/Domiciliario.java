package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Usuario;

public class Domiciliario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domiciliario);

        Button btnPedidosEnEspera = findViewById(R.id.btnPedidosEnEspera);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        btnPedidosEnEspera.setOnClickListener(view -> {
            Intent i = new Intent(Domiciliario.this, PedidosEnEspera.class);
            i.putExtra("user", user);
            startActivity(i);
        });
    }
}