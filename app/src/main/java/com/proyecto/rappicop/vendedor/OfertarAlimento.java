package com.proyecto.rappicop.vendedor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class OfertarAlimento extends AppCompatActivity {

    Button subir;
    Button enviar;
    ImageView image;

    EditText name;
    Spinner categoria;
    EditText precio;
    EditText ubicacion;
    EditText descripcion;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofertarcomida);

        enviar = findViewById(R.id.btnEnviarOferta);

        name = findViewById(R.id.name);
        categoria = findViewById(R.id.spinner);
        precio = findViewById(R.id.precio);
        ubicacion = findViewById(R.id.ubicacion);
        descripcion = findViewById(R.id.descripcion);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");
        String usuario = user.getUsuario();

//        subir.setOnClickListener(view -> {
//            Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            intent1.setType("image/");
//            startActivityForResult(intent1.createChooser(intent1, "Seleccione la imagen"), 10);
//        });
//
        enviar.setOnClickListener(view -> {
            String namext = name.getText().toString();
            String catgtxt = String.valueOf(categoria.getSelectedItem());
            int precionum = Integer.parseInt(precio.getText().toString());
            String ubitxt = ubicacion.getText().toString();
            String destxt = descripcion.getText().toString();
//            byte[] imagebyte = imageViewByte(image);

            Logica crearoferta = new Logica(OfertarAlimento.this);
            long id = crearoferta.nuevaOferta(usuario, namext, catgtxt, precionum, ubitxt, destxt, null);

            if (id > 0) {
                Toast.makeText(OfertarAlimento.this, "Oferta creada", Toast.LENGTH_LONG).show();
                Intent i = new Intent(OfertarAlimento.this, Vendedor.class);
                i.putExtra("user", user);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(OfertarAlimento.this, "Oferta rechazada", Toast.LENGTH_LONG).show();
            }
        });
    }

    private byte[] imageViewByte(ImageView picture) {
        Bitmap bitmap = ((BitmapDrawable) picture.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}