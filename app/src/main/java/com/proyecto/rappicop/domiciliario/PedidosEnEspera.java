package com.proyecto.rappicop.domiciliario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.AdaptadorOfertaEnEspera;
import com.proyecto.rappicop.modelos.Oferta;
import com.proyecto.rappicop.modelos.OfertaAceptada;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;

public class PedidosEnEspera extends AppCompatActivity {

    Logica iu = new Logica(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidosdomi);

        TextView usu = findViewById(R.id.usuario);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        usu.setText(user.getUsuario());

        ArrayList<OfertaAceptada> listaofertas = iu.consultaPedidosEspera();

        init(listaofertas, user);
    }

    public void init(ArrayList<OfertaAceptada> listaofertas, Usuario user) {
        for (OfertaAceptada x : listaofertas) {
            Oferta oferta = iu.consultaOfertaPorNombre(x.getOferta());
            Bitmap bitmap = BitmapFactory.decodeByteArray(oferta.getImagen(), 0, oferta.getImagen().length);
            x.setImagen(bitmap);
        }

        AdaptadorOfertaEnEspera adaptadorLista = new AdaptadorOfertaEnEspera(listaofertas, this, user);
        RecyclerView recyclerView = findViewById(R.id.listaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }
}
