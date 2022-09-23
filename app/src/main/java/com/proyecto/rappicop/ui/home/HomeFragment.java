package com.proyecto.rappicop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.adaptadores.HomeHoriAdaptador;
import com.proyecto.rappicop.adaptadores.HomeVertiAdaptador;
import com.proyecto.rappicop.adaptadores.UpdateVertical;
import com.proyecto.rappicop.modelos.HomeHoriModelo;
import com.proyecto.rappicop.modelos.HomeVertiModelo;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVertical {

    RecyclerView homehorizontal,homevertical;
    ArrayList<HomeHoriModelo> homeHoriModeloList;
    HomeHoriAdaptador homeHoriAdaptador;


    /////////////////////Vertical
    ArrayList<HomeVertiModelo> homeVertiModelolist;
      HomeVertiAdaptador homeVertiAdaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container, false);


        homehorizontal = root.findViewById(R.id.home_hori);
        homevertical = root.findViewById(R.id.home_verti);

        /////////////////////Horizontal RecyclerView
        homeHoriModeloList = new ArrayList<>();

        homeHoriModeloList.add(new HomeHoriModelo(R.drawable.pizza,"Pizza"));
        homeHoriModeloList.add(new HomeHoriModelo(R.drawable.hamburguesa,"Hamburgesa"));
        homeHoriModeloList.add(new HomeHoriModelo(R.drawable.francesa,"Papas Francesas"));
        homeHoriModeloList.add(new HomeHoriModelo(R.drawable.helado,"Helado"));
        homeHoriModeloList.add(new HomeHoriModelo(R.drawable.sandwich,"Sandwich"));


        homeHoriAdaptador = new HomeHoriAdaptador(this,getActivity(),homeHoriModeloList);
        homehorizontal.setAdapter(homeHoriAdaptador);
        homehorizontal.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homehorizontal.setHasFixedSize(true);
        homehorizontal.setNestedScrollingEnabled(false);



        /////////////////////Vertical RecyclerView
        homeVertiModelolist = new ArrayList<>();


        homeVertiAdaptador = new HomeVertiAdaptador(getActivity(),homeVertiModelolist);
        homevertical.setAdapter(homeVertiAdaptador);
        homevertical.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        return root;
    }


    @Override
    public void callback(int position, ArrayList<HomeVertiModelo> list) {

        homeVertiAdaptador = new HomeVertiAdaptador(getContext(),list);
        homeVertiAdaptador.notifyDataSetChanged();
        homevertical.setAdapter(homeVertiAdaptador);


    }
}