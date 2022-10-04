package com.proyecto.rappicop.ui.comidadiaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.ComidaDiariaAdaptador;
import com.proyecto.rappicop.modelos.ComidaDiariaModelo;

import java.util.ArrayList;
import java.util.List;


public class ComidaDiariaFragment extends Fragment {

    RecyclerView recyclerView;
    List<ComidaDiariaModelo> comidaDiariaModelos;
    ComidaDiariaAdaptador comidaDiariaAdaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.comida_diaria_fragment,container, false);

        recyclerView=root.findViewById(R.id.comida_diaria);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        comidaDiariaModelos=new ArrayList<>();

        comidaDiariaModelos.add(new ComidaDiariaModelo(R.drawable.desayuno,"Desayuno","30% Descuento","Descripción","Desayuno"));
        comidaDiariaModelos.add(new ComidaDiariaModelo(R.drawable.almuerzo,"Almuerzo","20% Descuento","Descripción","Almuerzo"));
        comidaDiariaModelos.add(new ComidaDiariaModelo(R.drawable.cena,"Cena","40% Descuento","Descripción","Cena"));
        comidaDiariaModelos.add(new ComidaDiariaModelo(R.drawable.postre,"Postre","15% Descuento","Descripción","Postre"));
        comidaDiariaModelos.add(new ComidaDiariaModelo(R.drawable.cafe,"Cafe","5% Descuento","Descripción","Cafe"));

        comidaDiariaAdaptador=new ComidaDiariaAdaptador(getContext(),comidaDiariaModelos);
        recyclerView.setAdapter(comidaDiariaAdaptador);
        comidaDiariaAdaptador.notifyDataSetChanged();


        return root;
    }

}
