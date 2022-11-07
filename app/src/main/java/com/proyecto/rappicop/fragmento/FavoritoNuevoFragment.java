package com.proyecto.rappicop.fragmento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.vendedor.ListaElementos;
import com.proyecto.rappicop.vendedor.Oferta;
import com.proyecto.rappicop.vendedor.OfertaAdaptador;

import java.util.ArrayList;
import java.util.List;

public class FavoritoNuevoFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorito_nuevo, container, false);

        recyclerView = view.findViewById(R.id.listaOfertasFavorito);

        Logica iu = new Logica(getActivity());
        ArrayList<Oferta> listaofertas = iu.consultaOfertas();

        init(listaofertas);
        return view;
    }

    public void init(ArrayList<Oferta> listaofertas) {
        List<ListaElementos> elements = new ArrayList<>();

        for (Oferta x : listaofertas) {
            String palo = "" + x.getPrecio();
            elements.add(new ListaElementos(null, x.getNombre(), x.getUbicacion(), palo, x.getUsuario()));
        }

        OfertaAdaptador adaptadorLista = new OfertaAdaptador(elements, getActivity(), false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorLista);
    }
}