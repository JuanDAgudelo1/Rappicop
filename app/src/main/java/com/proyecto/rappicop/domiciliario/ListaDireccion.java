package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.AdaptadorDirecciones;
import com.proyecto.rappicop.modelos.CarritoModelo;
import com.proyecto.rappicop.modelos.Direccion;
import com.proyecto.rappicop.modelos.ListaCarritoModelo;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ListaDireccion extends AppCompatActivity {

    String usuario = "";
    Usuario user;
    ListaCarritoModelo listaCarroCompras;
    Logica iu = new Logica(ListaDireccion.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadir);

        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra("user");
        listaCarroCompras = (ListaCarritoModelo) intent.getSerializableExtra("producto");

        usuario = user.getUsuario();

        Button anadir = findViewById(R.id.anadir);
        anadir.setOnClickListener(view -> {
            Intent i = new Intent(ListaDireccion.this, AgregarDireccion.class);
            i.putExtra("user", user);
            startActivity(i);
        });

        init();
    }

    public void init() {
        List<Direccion> elements = new ArrayList<>();
        ArrayList<Direccion> listadir = iu.consultaUbicacion(usuario);

        for (Direccion x : listadir) {
            elements.add(new Direccion(x.getNombre(), x.getDireccion()));
        }

        AdaptadorDirecciones adaptadorDirecciones = new AdaptadorDirecciones(elements, this, user, listaCarroCompras);
        RecyclerView recyclerView = findViewById(R.id.listadedirecciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorDirecciones);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
}
