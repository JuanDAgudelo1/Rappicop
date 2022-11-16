package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.vendedor.OfertarAlimento;

import java.util.ArrayList;
import java.util.List;

public class listadir extends AppCompatActivity {
    public static final String EXTRA_CODE="0";

    List<listadirecciones> elements;
    ArrayList<listadirecciones> listadir;
    RecyclerView recyclerView;
    AdaptadorDirecciones adaptadorDirecciones;

    String usu;

    Button anadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadir);

        DatabaseHelper rapidb = new DatabaseHelper(listadir.this);
        Logica iu = new Logica(listadir.this);
        Intent intent=getIntent();
        String id = intent.getStringExtra(EXTRA_CODE);

        usu = iu.idbusca(Integer.parseInt(id));

        anadir = (Button) findViewById(R.id.anadir);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(listadir.this, AgregarDireccion.class);
                i.putExtra(OfertarAlimento.EXTRA_MESSAGE, id);
                startActivity(i);
            }
        });

        listadir = iu.consultaubi(usu);
        init();

        adaptadorDirecciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listadirecciones seleccion = listadir.get(recyclerView.getChildAdapterPosition(view));
                Intent i = new Intent(listadir.this, AceptarOferta.class);
                String[] cap = {seleccion.getDireccion(),id};
                i.putExtra(String.valueOf(DescripcionOferta.EXTRA_MESSAGE), cap);
                startActivity(i);
            }
        });

    }

    public void  init() {
        elements = new ArrayList<>();

        for (listadirecciones x: listadir) {

            elements.add(new listadirecciones(x.getNombre(), x.getDireccion()));
        }

        adaptadorDirecciones = new AdaptadorDirecciones(elements, this,usu);
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
