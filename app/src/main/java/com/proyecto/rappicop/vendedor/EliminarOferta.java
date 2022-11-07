package com.proyecto.rappicop.vendedor;

import android.content.Intent;
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

public class EliminarOferta extends AppCompatActivity {

    ArrayList<Oferta> listaofertas;
    RecyclerView recyclerView;
    EliminarAdaptador adaptadorLista;

    TextView usu;
    TextView tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminaroferta);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");
        String usuario = user.getUsuario();

        Logica iu = new Logica(EliminarOferta.this);

        usu = findViewById(R.id.usuario);
        tipo = findViewById(R.id.tipo);
        tipo.setText(user.getRol());
        usu.setText(usuario);

        listaofertas = iu.consultaOfertasPorUsuario(usuario);
        init();
    }

    public void init() {
        List<ListaElementos> elements = new ArrayList<>();

        for (Oferta x : listaofertas) {
            String palo = "" + x.getPrecio();
            // TODO: ajustar imagen
//            Bitmap bim = BitmapFactory.decodeByteArray(x.getImagen(), 0, x.getImagen().length);

            elements.add(new ListaElementos(null, x.getNombre(), x.getUbicacion(), palo, x.getUsuario()));
        }

        adaptadorLista = new EliminarAdaptador(elements, this, usu.getText().toString());

        recyclerView = findViewById(R.id.listaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}