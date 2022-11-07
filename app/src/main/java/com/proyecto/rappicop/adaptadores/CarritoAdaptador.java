package com.proyecto.rappicop.adaptadores;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.CarritoModelo;
import com.proyecto.rappicop.modelos.ListaCarritoModelo;
import com.proyecto.rappicop.modelos.Usuario;
import com.proyecto.rappicop.vendedor.Oferta;
import com.proyecto.rappicop.vendedor.OfertaConsumidor;

import java.util.ArrayList;
import java.util.List;

public class CarritoAdaptador extends AppCompatActivity {

    ArrayList<CarritoModelo> listacarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tu_carrito);

        TextView mensaje = findViewById(R.id.mensajecarrito);
        Button boton = findViewById(R.id.iroferta);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");
        String usuario = user.getUsuario();

        Logica iu = new Logica(CarritoAdaptador.this);

        listacarrito = new ArrayList<>();
        listacarrito = iu.consultaCarroPorUsuario(usuario);

        mensaje.setVisibility(View.VISIBLE);
        boton.setVisibility(View.VISIBLE);

        init();

        boton.setOnClickListener(view -> {
            Intent i = new Intent(CarritoAdaptador.this, OfertaConsumidor.class);
            i.putExtra("user", user);
            startActivity(i);
        });
    }

    public void init() {
        List<ListaCarritoModelo> elements = new ArrayList<>();
        Logica iu = new Logica(CarritoAdaptador.this);

        for (CarritoModelo x : listacarrito) {
            String palo = "" + x.getCantidad();
            Oferta oferta = iu.consultaOfertaPorNombre(x.getProducto());
            Bitmap bim = BitmapFactory.decodeByteArray(oferta.getImagen(), 0, oferta.getImagen().length);
            elements.add(new ListaCarritoModelo(bim, oferta.getNombre(), "$" + oferta.getPrecio(), oferta.getDescripcion(), "" + x.getCantidad(), x.getConsumidor(), x.getVendedor()));
        }

        AdaptadorProducto adaptadorProducto = new AdaptadorProducto(elements, this);

        RecyclerView recyclerView = findViewById(R.id.listadeproductos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorProducto);
    }

    @Override
    protected void onStop() {
        super.onStop();

        finish();
    }
}