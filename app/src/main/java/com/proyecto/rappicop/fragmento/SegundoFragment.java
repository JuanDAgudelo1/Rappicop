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
import com.proyecto.rappicop.adaptadores.PopularAdaptador;
import com.proyecto.rappicop.adaptadores.PopularVerAdaptador;
import com.proyecto.rappicop.modelos.DestacadoModelo;
import com.proyecto.rappicop.modelos.DestacadoVerModelo;
import com.proyecto.rappicop.modelos.PopularModelo;
import com.proyecto.rappicop.modelos.PopularVerModelo;

import java.util.ArrayList;
import java.util.List;


public class SegundoFragment extends Fragment {





    ////////////////////// Popular Horizontal RecyclerView
    List<PopularModelo> popularModeloList;
    RecyclerView recyclerView;
    PopularAdaptador popularAdaptador;

    ////////////////////// Popular Vertical RecyclerView
    List<PopularVerModelo> popularVerModeloList;
    RecyclerView recyclerView2;
    PopularVerAdaptador popularVerAdaptador;

    public SegundoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_segundo, container, false);



        ////////////////////// Popular Horizontal RecyclerView

        recyclerView = view.findViewById(R.id.popular_hor_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        popularModeloList = new ArrayList<>();

        popularModeloList.add(new PopularModelo(R.drawable.helado3,"Destacado 1", "Descripción 1"));
        popularModeloList.add(new PopularModelo(R.drawable.papa2,"Destacado 2", "Descripción 2"));
        popularModeloList.add(new PopularModelo(R.drawable.cafe2,"Destacado 3", "Descripción 3"));

        popularAdaptador = new PopularAdaptador(popularModeloList);
        recyclerView.setAdapter(popularAdaptador);


        ////////////////////// Popular Vertical RecyclerView

        recyclerView2 = view.findViewById(R.id.popular_ver_rec);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        popularVerModeloList = new ArrayList<>();

        popularVerModeloList.add(new PopularVerModelo(R.drawable.hamburgesa3,"Destacado 1","Descripcion 1","4.8","10:00 - 8:00"));
        popularVerModeloList.add(new PopularVerModelo(R.drawable.papa1,"Destacado 2","Descripcion 2","4.8","10:00 - 8:00"));
        popularVerModeloList.add(new PopularVerModelo(R.drawable.pizza2,"Destacado 3","Descripcion 3","4.8","10:00 - 8:00"));

        popularVerAdaptador = new PopularVerAdaptador(popularVerModeloList);
        recyclerView2.setAdapter(popularVerAdaptador);

        return view;
    }
}