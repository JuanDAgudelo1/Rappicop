package com.proyecto.rappicop.vendedor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;

public class DescripcionOferta extends AppCompatActivity {

    public static final String[] EXTRA_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_oferta);

        Intent intent = getIntent();

        String[] dato = intent.getStringArrayExtra(String.valueOf(EXTRA_MESSAGE));
        String nombre = dato[0];
        String cliente = dato[1];

        Logica iu = new Logica(DescripcionOferta.this);
        Oferta oferta = iu.consultaOfertaPorNombre(nombre);

        TextView name = findViewById(R.id.name);
        TextView precio = findViewById(R.id.precio);
        TextView ubi = findViewById(R.id.gps);
        TextView descripcion = findViewById(R.id.description);
        ImageView img = findViewById(R.id.picture);
        Button eliminarbtn = findViewById(R.id.eliminarbtn);

        name.setText(oferta.getNombre());
        precio.setText(String.valueOf(oferta.getPrecio()));
        ubi.setText(oferta.getUbicacion());
        descripcion.setText(oferta.getDescripcion());

        Bitmap bim = BitmapFactory.decodeByteArray(oferta.getImagen(), 0, oferta.getImagen().length);
        img.setImageBitmap(bim);

        eliminarbtn.setOnClickListener(view -> {
            if (iu.verificarCarrito(cliente, oferta.getUsuario(), oferta.getNombre())) {
                boolean aumentarCantidad = iu.aumentarCantidadCarrito(cliente, oferta.getUsuario(), nombre);
                Toast.makeText(DescripcionOferta.this, aumentarCantidad ? "Aumenta cantidad" : "No aumenta", Toast.LENGTH_LONG).show();
            } else {
                long id = iu.nuevoCarrito(cliente, oferta.getUsuario(), nombre, 1);
                Toast.makeText(DescripcionOferta.this, id > 0 ? "Agregado" : "No fue agregada", Toast.LENGTH_LONG).show();
            }
        });
    }
}