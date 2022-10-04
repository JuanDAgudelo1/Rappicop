package com.proyecto.rappicop.actividades;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.DetalleComidaDiariaAdaptador;
import com.proyecto.rappicop.modelos.DetalleComidaDiariaModelo;

import java.util.ArrayList;
import java.util.List;

public class DetalleComidiaDiariaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DetalleComidaDiariaModelo> detalleComidaDiariaModeloList;
    DetalleComidaDiariaAdaptador comidaDiariaAdaptador;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_comidia_diaria);

        String tipo =  getIntent().getStringExtra("tipo");

        recyclerView=findViewById(R.id.detalle);
        imageView=findViewById(R.id.detalle_im);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detalleComidaDiariaModeloList = new ArrayList<>();
        comidaDiariaAdaptador= new DetalleComidaDiariaAdaptador(detalleComidaDiariaModeloList);
        recyclerView.setAdapter(comidaDiariaAdaptador);

        if(tipo !=null && tipo.equalsIgnoreCase("desayuno")){
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.fav1,"Desayuno","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.fav2,"Desayuno","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.fav3,"Desayuno","Descripción","4.4","12.000","8Am a 10Pm"));
            comidaDiariaAdaptador.notifyDataSetChanged();
        }
        if(tipo !=null && tipo.equalsIgnoreCase("almuerzo")){
            imageView.setImageResource(R.drawable.almuerzo);
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.almuerzo1,"Almuerzo","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.almuerzo2,"Almuerzo","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.almuerzo3,"Almuerzo","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.almuerzo4,"Almuerzo","Descripción","4.4","12.000","8Am a 10Pm"));
            comidaDiariaAdaptador.notifyDataSetChanged();
        }
        if(tipo !=null && tipo.equalsIgnoreCase("cena")){
            imageView.setImageResource(R.drawable.cena);
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.cena1,"Cena","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.cena2,"Cena","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.cena3,"Cena","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.cena4,"Cena","Descripción","4.4","12.000","8Am a 10Pm"));

            comidaDiariaAdaptador.notifyDataSetChanged();
        }
        if(tipo !=null && tipo.equalsIgnoreCase("postre")){
            imageView.setImageResource(R.drawable.postre);
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.s1,"Postre","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.s2,"Postre","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.s3,"Postre","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.s4,"Postre","Descripción","4.4","12.000","8Am a 10Pm"));
            comidaDiariaAdaptador.notifyDataSetChanged();
        }
        if(tipo !=null && tipo.equalsIgnoreCase("cafe")){
            imageView.setImageResource(R.drawable.cafe);
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.cafe1,"Cafe","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.cafe2,"Cafe","Descripción","4.4","12.000","8Am a 10Pm"));
            detalleComidaDiariaModeloList.add(new DetalleComidaDiariaModelo(R.drawable.cafe3,"Cafe","Descripción","4.4","12.000","8Am a 10Pm"));
            comidaDiariaAdaptador.notifyDataSetChanged();
        }

    }
}