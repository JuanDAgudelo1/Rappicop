package com.proyecto.rappicop.fragmento;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.DestacadoAdaptador;
import com.proyecto.rappicop.adaptadores.DestacadoVerAdaptador;
import com.proyecto.rappicop.modelos.DestacadoModelo;
import com.proyecto.rappicop.modelos.DestacadoVerModelo;

import java.util.ArrayList;
import java.util.List;


public class PrimerFragment extends Fragment {





    ////////////////////// Destacado Horizontal RecyclerView
    List<DestacadoModelo> destacadoModeloList;
    RecyclerView recyclerView;
    DestacadoAdaptador destacadoAdaptador;

    ////////////////////// Destacado Vertical RecyclerView
    List<DestacadoVerModelo> destacadoVerModeloList;
    RecyclerView recyclerView2;
    DestacadoVerAdaptador destacadoVerAdaptador;

    public PrimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_primer, container, false);



        ////////////////////// Destacado Horizontal RecyclerView

        recyclerView = view.findViewById(R.id.destacado_hor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        destacadoModeloList = new ArrayList<>();

        destacadoModeloList.add(new DestacadoModelo(R.drawable.fav1,"Destacado 1", "Descripción 1"));
        destacadoModeloList.add(new DestacadoModelo(R.drawable.fav2,"Destacado 2", "Descripción 2"));
        destacadoModeloList.add(new DestacadoModelo(R.drawable.fav3,"Destacado 3", "Descripción 3"));

        destacadoAdaptador = new DestacadoAdaptador(destacadoModeloList);
        recyclerView.setAdapter(destacadoAdaptador);


        ////////////////////// Destacado Vertical RecyclerView

        recyclerView2 = view.findViewById(R.id.destacado_ver_rec);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        destacadoVerModeloList = new ArrayList<>();

        destacadoVerModeloList.add(new DestacadoVerModelo(R.drawable.ver1,"Destacado 1","Descripcion 1","4.8","10:00 - 8:00"));
        destacadoVerModeloList.add(new DestacadoVerModelo(R.drawable.ver2,"Destacado 2","Descripcion 2","4.8","10:00 - 8:00"));
        destacadoVerModeloList.add(new DestacadoVerModelo(R.drawable.ver3,"Destacado 3","Descripcion 3","4.8","10:00 - 8:00"));

        destacadoVerModeloList.add(new DestacadoVerModelo(R.drawable.ver1,"Destacado 1","Descripcion 1","4.8","10:00 - 8:00"));
        destacadoVerModeloList.add(new DestacadoVerModelo(R.drawable.ver2,"Destacado 2","Descripcion 2","4.8","10:00 - 8:00"));
        destacadoVerModeloList.add(new DestacadoVerModelo(R.drawable.ver3,"Destacado 3","Descripcion 3","4.8","10:00 - 8:00"));

        destacadoVerAdaptador = new DestacadoVerAdaptador(destacadoVerModeloList);
        recyclerView2.setAdapter(destacadoVerAdaptador);

       return view;
    }
}