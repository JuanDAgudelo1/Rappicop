package com.proyecto.rappicop.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.domiciliario.Domipedidos;
import com.proyecto.rappicop.modelos.Usuario;

public class Domilciliario extends AppCompatActivity {


    Button consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domiciliario);

        consulta = (Button) findViewById(R.id.pedido);

        Intent intent=getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Domilciliario.this, Domipedidos.class);
                i.putExtra("user", user);
                startActivity(i);
            }
        });

    }
}