package com.proyecto.rappicop.actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.ListaElementos;
import com.proyecto.rappicop.modelos.Oferta;
import com.proyecto.rappicop.modelos.Usuario;
import com.proyecto.rappicop.vendedor.DescripcionOferta;

import java.util.ArrayList;
import java.util.List;

public class OfertaConsumidor extends AppCompatActivity {

    ArrayList<Oferta> listaofertas;
    RecyclerView recyclerView;
    ListaAdaptador adaptadorLista;

    TextView usu;
    TextView tipo;
    private SearchView searchView;

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
        searchView = findViewById(R.id.barraBusqueda);

        tipo.setText(rol);
        usu.setText(usuario);

        if (rol.equals("Vendedor")) {
            listaofertas = iu.consultaOfertasPorUsuario(usuario);
        } else {
            listaofertas = iu.consultaOfertas();
        }

        init();


        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrarProductos(newText);
                return false;
            }
        });

        adaptadorLista.setOnClickListener(view -> {
            Oferta oferta = listaofertas.get(recyclerView.getChildAdapterPosition(view));
            Intent i = new Intent(OfertaConsumidor.this, DescripcionOferta.class);

            i.putExtra("oferta", oferta);
            i.putExtra("user", user);

            startActivity(i);
        });
    }

    public void init() {
        List<ListaElementos> elements = new ArrayList<>();

        for (Oferta x : listaofertas) {
            String palo = "" + x.getPrecio();
            Bitmap bim = x.getImagen() == null ? null : BitmapFactory.decodeByteArray(x.getImagen(), 0, x.getImagen().length);
            elements.add(new ListaElementos(bim, x.getNombre(), x.getUbicacion(), palo, x.getUsuario()));
        }

        adaptadorLista = new ListaAdaptador(elements, this, usu.getText().toString());

        recyclerView = findViewById(R.id.listaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }

    private void filtrarProductos(String texto) {
        List<ListaElementos> nuevaLista = new ArrayList<>();

        for (Oferta x : listaofertas) {
            if (x.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                String palo = "" + x.getPrecio();
                Bitmap bim = x.getImagen() == null ? null : BitmapFactory.decodeByteArray(x.getImagen(), 0, x.getImagen().length);
                nuevaLista.add(new ListaElementos(bim, x.getNombre(), x.getUbicacion(), palo, x.getUsuario()));
            }
        }

        if (nuevaLista.isEmpty()) {
            Toast.makeText(this, "No productos con esta busqueda, cambia de filtrado...", Toast.LENGTH_SHORT).show();
        }

        adaptadorLista.setFiltradoProductos(nuevaLista);
    }
}