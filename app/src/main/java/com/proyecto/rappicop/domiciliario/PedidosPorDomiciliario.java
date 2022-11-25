package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.AdaptadorOfertaAceptada;
import com.proyecto.rappicop.modelos.OfertaAceptada;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;
import java.util.Objects;

public class PedidosPorDomiciliario extends AppCompatActivity {

    Logica iu = new Logica(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_por_domiciliario);

        TextView usu = findViewById(R.id.usuario);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        usu.setText(user.getUsuario());

        ArrayList<OfertaAceptada> listaofertas = iu.consultaPedidosPorDomicilario(user.getUsuario());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            listaofertas.removeIf(oferta -> Objects.equals(oferta.getEstado(), "espera"));
        }

        init(listaofertas, user);
    }

    public void init(ArrayList<OfertaAceptada> listaofertas, Usuario user) {
        AdaptadorOfertaAceptada adaptadorLista = new AdaptadorOfertaAceptada(listaofertas, this, user);
        RecyclerView recyclerView = findViewById(R.id.listaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }
}