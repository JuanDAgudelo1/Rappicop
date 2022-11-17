package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.AdaptadorDirecciones;
import com.proyecto.rappicop.modelos.Direccion;

import java.util.ArrayList;
import java.util.List;

public class ListaDireccion extends AppCompatActivity {
    public static final String EXTRA_CODE = "0";

    List<Direccion> elements;
    ArrayList<Direccion> listadir;
    RecyclerView recyclerView;
    AdaptadorDirecciones adaptadorDirecciones;

    String usu;

    Button anadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadir);

        Logica iu = new Logica(ListaDireccion.this);
        Intent intent = getIntent();
        String id = intent.getStringExtra(EXTRA_CODE);

        usu = iu.idbusca(Integer.parseInt(id));

        anadir = findViewById(R.id.anadir);

        anadir.setOnClickListener(view -> {
            Intent i = new Intent(ListaDireccion.this, AgregarDireccion.class);
            i.putExtra("mesagge", id);
            startActivity(i);
        });

        listadir = iu.consultaUbicacion(usu);
        init();

        adaptadorDirecciones.setOnClickListener(view -> {
            Direccion seleccion = listadir.get(recyclerView.getChildAdapterPosition(view));
            Intent i = new Intent(ListaDireccion.this, AceptarOferta.class);
            String[] cap = {seleccion.getDireccion(), id};
            i.putExtra(String.valueOf(DescripcionOferta.EXTRA_MESSAGE), cap);
            startActivity(i);
        });

    }

    public void init() {
        elements = new ArrayList<>();

        for (Direccion x : listadir) {
            elements.add(new Direccion(x.getNombre(), x.getDireccion()));
        }

        adaptadorDirecciones = new AdaptadorDirecciones(elements, this, usu);
        recyclerView = findViewById(R.id.listadedirecciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorDirecciones);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
