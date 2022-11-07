package com.proyecto.rappicop.vendedor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class OfertaConsumidor extends AppCompatActivity {

    ArrayList<Oferta> listaofertas;
    RecyclerView recyclerView;
    ListaAdaptador adaptadorLista;

    TextView usu;
    TextView tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertasconsumidor);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");
        String usuario = user.getUsuario();
        String rol = user.getRol();

        Logica iu = new Logica(OfertaConsumidor.this);
        listaofertas = new ArrayList<>();

        usu = findViewById(R.id.usuario);
        tipo = findViewById(R.id.tipo);

        tipo.setText(rol);
        usu.setText(usuario);

        if (rol.equals("Vendedor")) {
            listaofertas = iu.consultaOfertasPorUsuario(usuario);
        } else {
            listaofertas = iu.consultaOfertas();
        }

        init();

        adaptadorLista.setOnClickListener(view -> {
            Oferta oferta = listaofertas.get(recyclerView.getChildAdapterPosition(view));
            Intent i = new Intent(OfertaConsumidor.this, DescripcionOferta.class);
            String[] cap = {oferta.getNombre(), usuario};
            i.putExtra("user", user);
            startActivity(i);
        });
    }

    public void init() {
        List<ListaElementos> elements = new ArrayList<>();

        for (Oferta x : listaofertas) {
            String palo = "" + x.getPrecio();
            Bitmap bim = BitmapFactory.decodeByteArray(x.getImagen(), 0, x.getImagen().length);

            elements.add(new ListaElementos(bim, x.getNombre(), x.getUbicacion(), palo, x.getUsuario()));
        }

        adaptadorLista = new ListaAdaptador(elements, this, usu.getText().toString());

        recyclerView = findViewById(R.id.listaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }
}