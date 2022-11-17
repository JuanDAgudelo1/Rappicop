package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Oferta;

import java.util.ArrayList;
import java.util.List;

public class ofertasaceptadas extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="mesagge";
    List<Listadeelementos> elements;
    ArrayList<OfertaAceptada> listaofertas;
    RecyclerView recyclerView;
    AdaptadorAceptada adaptadorLista;
    String usuario;

    DatabaseHelper rapidb = new DatabaseHelper(ofertasaceptadas.this);
    Logica iu = new Logica(ofertasaceptadas.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertasaceptadas);


        Intent intent=getIntent();
        usuario = intent.getStringExtra(EXTRA_MESSAGE);
        listaofertas = new ArrayList<OfertaAceptada>();

        listaofertas = iu.consultaAceptadas(usuario);
        init();

        adaptadorLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void  init() {
        elements = new ArrayList<>();

        for (OfertaAceptada x: listaofertas) {
            Oferta oferta = iu.consultanombre(x.getOferta());
            Bitmap bim = BitmapFactory.decodeByteArray(oferta.getImagen(),0,oferta.getImagen().length);
            elements.add(new Listadeelementos(bim, x.getOferta(), x.getUbicacion(), x.getEstado(), usuario));
        }

        adaptadorLista = new AdaptadorAceptada(elements, this,usuario);
        recyclerView = findViewById(R.id.lista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }

}