package com.proyecto.rappicop.vendedor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Usuario;

public class DescripcionOferta extends AppCompatActivity {

    public static final String[] EXTRA_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_oferta);

        Intent intent = getIntent();

        Oferta oferta = (Oferta) intent.getSerializableExtra("oferta");
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        Logica iu = new Logica(DescripcionOferta.this);

        TextView name = findViewById(R.id.name);
        TextView precio = findViewById(R.id.precio);
        TextView ubi = findViewById(R.id.gps);
        TextView descripcion = findViewById(R.id.description);
        ImageView img = findViewById(R.id.picture);
        Button btnAgregarCarro = findViewById(R.id.btnAgregarCarro);

        name.setText(oferta.getNombre());
        precio.setText(String.valueOf(oferta.getPrecio()));
        ubi.setText(oferta.getUbicacion());
        descripcion.setText(oferta.getDescripcion());

//        Bitmap bim = BitmapFactory.decodeByteArray(oferta.getImagen(), 0, oferta.getImagen().length);
//        img.setImageBitmap(bim);

        btnAgregarCarro.setOnClickListener(view -> {
            if (iu.verificarCarrito(user.getNombre(), oferta.getUsuario(), oferta.getNombre())) {
                boolean aumentarCantidad = iu.aumentarCantidadCarrito(user.getNombre(), oferta.getUsuario(), oferta.getNombre());
                Toast.makeText(DescripcionOferta.this, aumentarCantidad ? "Aumenta cantidad" : "No aumenta", Toast.LENGTH_LONG).show();
            } else {
                long id = iu.nuevoCarrito(user.getNombre(), oferta.getUsuario(), oferta.getNombre(), 1);
                Toast.makeText(DescripcionOferta.this, id > 0 ? "Agregado" : "No fue agregada", Toast.LENGTH_LONG).show();
            }
        });
    }
}