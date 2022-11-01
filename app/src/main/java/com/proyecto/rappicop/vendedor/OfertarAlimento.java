package com.proyecto.rappicop.vendedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class OfertarAlimento extends AppCompatActivity {

    public static final String EXTRA_MESSAGE="mesagge";

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

        enviar = (Button) findViewById(R.id.enviar);

        name = (EditText) findViewById(R.id.name);
        categoria = (Spinner) findViewById(R.id.spinner);
        precio = (EditText) findViewById(R.id.precio);
        ubicacion = (EditText) findViewById(R.id.ubicacion);
        descripcion = (EditText) findViewById(R.id.descripcion);

        Intent intent=getIntent();
        String usuario = intent.getStringExtra(EXTRA_MESSAGE);


        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent,"Seleccione la imagen"),10);
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usrtxt = usuario;
                String namext = name.getText().toString();
                String catgtxt = String.valueOf(categoria.getSelectedItem());
                int precionum = Integer.parseInt(precio.getText().toString());
                String ubitxt = ubicacion.getText().toString();
                String destxt = descripcion.getText().toString();
                byte[] imagebyte = imageViewByte(image);

                Logica crearoferta = new Logica(OfertarAlimento.this);
                long id = crearoferta.nuevaOferta(usrtxt,namext,catgtxt,precionum,ubitxt,destxt, imagebyte);

                if(id > 0){
                    Toast.makeText(OfertarAlimento.this, "Oferta creada", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(OfertarAlimento.this, Vendedor.class);
                    i.putExtra(OfertarAlimento.EXTRA_MESSAGE, usuario);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(OfertarAlimento.this, "Oferta rechazada", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private byte[] imageViewByte(ImageView picture){

        Bitmap bitmap = ((BitmapDrawable)picture.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytearray = stream.toByteArray();
        return bytearray;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
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