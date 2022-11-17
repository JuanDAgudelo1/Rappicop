package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.CarritoModelo;
import com.proyecto.rappicop.ui.home.HomeFragment;
import com.proyecto.rappicop.modelos.Oferta;

public class AceptarOferta extends AppCompatActivity {

    public static final String[] EXTRA_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceptar_oferta);

        Logica iu = new Logica(AceptarOferta.this);
        Intent intent = getIntent();

        String[] dato = intent.getStringArrayExtra(String.valueOf(EXTRA_MESSAGE));
        String ubicacion = dato[0];
        String idcarro = dato[1];

        CarritoModelo carro = iu.consultarCarrito(Integer.parseInt(idcarro));
        Oferta oferta = iu.consultanombre(carro.getProducto());

        TextView name = findViewById(R.id.ubi);
        TextView cantidad = findViewById(R.id.cant);
        TextView ubi = findViewById(R.id.description);
        TextView fijo = findViewById(R.id.preciofijo);
        TextView total = findViewById(R.id.preciototal);
        ImageView img = findViewById(R.id.picture);
        Button aceptar = findViewById(R.id.eliminarbtn);

        name.setText(carro.getProducto());
        ubi.setText("Dirección de entrega: " + ubicacion);
        cantidad.setText("Cantidad: " + carro.getCantidad());
        fijo.setText("Precio Uni: " + oferta.getPrecio());
        total.setText("Precio Total: " + (oferta.getPrecio() * carro.getCantidad()));

        Bitmap bim = BitmapFactory.decodeByteArray(oferta.getImagen(), 0, oferta.getImagen().length);
        img.setImageBitmap(bim);

        aceptar.setOnClickListener(view -> {
            Intent init = new Intent(AceptarOferta.this, HomeFragment.class);

            AlertDialog.Builder alerta = new AlertDialog.Builder(AceptarOferta.this);
            alerta.setMessage("¿Esta seguro de esta compra?")
                    .setCancelable(false)
                    .setPositiveButton("Si", (dialogInterface, i) -> {
                        long id = iu.aceptarOferta(carro.getProducto(), carro.getConsumidor(), ubicacion, carro.getCantidad(), (oferta.getPrecio() * carro.getCantidad()), "espera");

                        if (id > 0) {
                            Toast.makeText(AceptarOferta.this, "Oferta aceptada", Toast.LENGTH_SHORT).show();
                            if (iu.eliminarCarritoCompras(carro.getConsumidor(), carro.getProducto())) {
                                Toast.makeText(AceptarOferta.this, "Carrito eliminado", Toast.LENGTH_SHORT).show();
                            }

                            init.putExtra(HomeFragment.EXTRA_MESSAGE, carro.getConsumidor());
                            startActivity(init);
                            finish();
                        } else {
                            Toast.makeText(AceptarOferta.this, "Oferta rechazada", Toast.LENGTH_LONG).show();
                        }

                        dialogInterface.cancel();
                        finish();
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
            AlertDialog titulo = alerta.create();
            titulo.setTitle("Aceptar compra");
            titulo.show();
        });

    }
}