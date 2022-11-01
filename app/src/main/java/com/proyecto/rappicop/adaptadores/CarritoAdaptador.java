package com.proyecto.rappicop.adaptadores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rapicoop.CarritoModelo;

import com.proyecto.rappicop.DB.DatabaseHelper;
import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.ListaCarritoModelo;
import com.proyecto.rappicop.vendedor.ListaAdaptador;
import com.proyecto.rappicop.vendedor.Oferta;
import com.proyecto.rappicop.vendedor.OfertaConsumidor;

import java.util.ArrayList;
import java.util.List;

public class CarritoAdaptador extends AppCompatActivity {

    public static final String EXTRA_MESSAGE="mesagge";
    List<ListaCarritoModelo> elements;
    ArrayList<com.example.rapicoop.CarritoModelo> listacarrito;
    RecyclerView recyclerView;
    AdaptadorProducto adaptadorProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tu_carrito);

        DatabaseHelper rapidb = new DatabaseHelper(CarritoAdaptador.this);
        Logica iu = new Logica(CarritoAdaptador.this);
        Intent intent=getIntent();
        String usuario = intent.getStringExtra(EXTRA_MESSAGE);

        listacarrito = new ArrayList<CarritoModelo>();
        listacarrito = iu.consultacarro(usuario);


        init();

        TextView mensaje = (TextView) findViewById(R.id.mensajecarrito);
        Button boton = (Button) findViewById(R.id.iroferta);

        if (elements.isEmpty()){

            mensaje.setVisibility(View.VISIBLE);
            boton.setVisibility(View.VISIBLE);

        }

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CarritoAdaptador.this, OfertaConsumidor.class);
                i.putExtra(OfertaConsumidor.EXTRA_MESSAGE, usuario);
                startActivity(i);
            }
        });

    }
    public void init(){

        elements = new ArrayList<>();
        Logica iu = new Logica(CarritoAdaptador.this);
        for (CarritoModelo x: listacarrito) {
            String palo = "" + x.getCantidad();
            Oferta oferta = iu.consultanombre(x.getProducto());
            Bitmap bim = BitmapFactory.decodeByteArray(oferta.getImagen(),0,oferta.getImagen().length);
            elements.add(new ListaCarritoModelo (bim, oferta.getNombre(), "$" + oferta.getPrecio(), oferta.getDescripcion(),"" + x.getCantidad(),x.getConsumidor(),x.getVendedor()));
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