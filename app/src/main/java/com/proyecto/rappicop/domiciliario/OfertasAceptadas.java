package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.AdaptadorOfertaAceptada;
import com.proyecto.rappicop.modelos.ListaElementos;
import com.proyecto.rappicop.modelos.Oferta;
import com.proyecto.rappicop.modelos.OfertaAceptada;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class OfertasAceptadas extends AppCompatActivity {

    Logica iu = new Logica(OfertasAceptadas.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertasaceptadas);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        String usuario = user.getUsuario();

        ArrayList<OfertaAceptada> listaOfertas = iu.consultaAceptadas(usuario);
        init(listaOfertas, user);
    }

    public void init(ArrayList<OfertaAceptada> listaOfertas, Usuario user) {
        List<ListaElementos> elements = new ArrayList<>();

        for (OfertaAceptada x : listaOfertas) {
            Oferta oferta = iu.consultanombre(x.getOferta());
//            Bitmap bim = BitmapFactory.decodeByteArray(oferta.getImagen(), 0, oferta.getImagen().length);
            elements.add(new ListaElementos(null, x.getOferta(), x.getUbicacion(), x.getEstado(), user.getUsuario()));
        }

//        AdaptadorOfertaAceptada adaptadorLista = new AdaptadorOfertaAceptada(elements, this, user.getUsuario());
//
//        RecyclerView recyclerView = findViewById(R.id.lista);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adaptadorLista);
    }

}