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
import com.proyecto.rappicop.modelos.Usuario;
import com.proyecto.rappicop.modelos.Oferta;

import java.util.ArrayList;
import java.util.List;

public class PedidosEnEspera extends AppCompatActivity {

    Logica iu = new Logica(PedidosEnEspera.this);

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
        List<Listadeelementos> elements = new ArrayList<>();

        for (OfertaAceptada x : listaofertas) {
            Oferta oferta = iu.consultanombre(x.getOferta());
            Bitmap bim = BitmapFactory.decodeByteArray(oferta.getImagen(), 0, oferta.getImagen().length);
            elements.add(new Listadeelementos(bim, x.getOferta(), x.getUbicacion(), x.getEstado(), user.getUsuario()));
        }

        AdaptadorEspera adaptadorLista = new AdaptadorEspera(elements, this, user.getUsuario());
        RecyclerView recyclerView = findViewById(R.id.listaRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorLista);
    }
}
