package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.AdaptadorOfertaEnEspera;
import com.proyecto.rappicop.modelos.OfertaAceptada;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;

public class PedidosEnEspera extends AppCompatActivity {

    Logica iu = new Logica(PedidosEnEspera.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidosdomi);

        TextView usu = findViewById(R.id.usuario);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        usu.setText(user.getUsuario());

        ArrayList<OfertaAceptada> listaofertas = iu.consultaPedidosEspera();

        init(listaofertas, user);
    }

    public void init(ArrayList<OfertaAceptada> listaofertas, Usuario user) {
        AdaptadorOfertaEnEspera adaptadorLista = new AdaptadorOfertaEnEspera(listaofertas, this, user.getUsuario());
        RecyclerView recyclerView = findViewById(R.id.listaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }
}
