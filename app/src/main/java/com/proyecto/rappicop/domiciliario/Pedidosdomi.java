package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.vendedor.Oferta;

import java.util.ArrayList;
import java.util.List;

public class Pedidosdomi extends AppCompatActivity {
    public static final String EXTRA_MESSAGE="mesagge";
    TextView usu;

    List<Listadeelementos> elements;
    ArrayList<OfertaAceptada> listaofertas;
    RecyclerView recyclerView;
    AdaptadorEspera adaptadorLista;
    String usuario;

    DatabaseHelper rapidb = new DatabaseHelper(Pedidosdomi.this);
    Logica iu = new Logica(Pedidosdomi.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidosdomi);

        Intent intent=getIntent();
        String usuario = intent.getStringExtra(EXTRA_MESSAGE);
        usu = (TextView) findViewById(R.id.usuario);
        usu.setText(usuario);

        listaofertas = new ArrayList<OfertaAceptada>();
        listaofertas = iu.consultaespera();
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

        adaptadorLista = new AdaptadorEspera(elements, this,usuario);
        recyclerView = findViewById(R.id.listaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }
}
