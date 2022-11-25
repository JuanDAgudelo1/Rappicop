package com.proyecto.rappicop.fragmento;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.ListaElementos;
import com.proyecto.rappicop.modelos.Oferta;
import com.proyecto.rappicop.vendedor.OfertaAdaptador;

import java.util.ArrayList;
import java.util.List;

public class FavoritoNuevoFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private OfertaAdaptador adaptadorLista;
    private ArrayList<Oferta> listaofertas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorito_nuevo, container, false);

        recyclerView = view.findViewById(R.id.listaOfertasFavorito);
        searchView = view.findViewById(R.id.barraBusqueda);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrarProductos(newText);
                return false;
            }
        });

        Logica iu = new Logica(getActivity());
        listaofertas = iu.consultaOfertas();

        init();
        return view;
    }

    public void init() {
        List<ListaElementos> elements = new ArrayList<>();

        for (Oferta x : listaofertas) {
            String palo = "" + x.getPrecio();
            Bitmap bim = x.getImagen() == null ? null : BitmapFactory.decodeByteArray(x.getImagen(), 0, x.getImagen().length);
            elements.add(new ListaElementos(bim, x.getNombre(), x.getUbicacion(), palo, x.getUsuario()));
        }

        adaptadorLista = new OfertaAdaptador(elements, getActivity(), false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorLista);
    }

    private void filtrarProductos(String texto) {
        List<ListaElementos> nuevaLista = new ArrayList<>();

        for (Oferta x : listaofertas) {
            if (x.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                String palo = "" + x.getPrecio();
                nuevaLista.add(new ListaElementos(null, x.getNombre(), x.getUbicacion(), palo, x.getUsuario()));
            }
        }

        if (nuevaLista.isEmpty()) {
            Toast.makeText(getActivity(), "No productos con esta busqueda, cambia de filtrado...", Toast.LENGTH_SHORT).show();
        }

        adaptadorLista.setFiltradoProductos(nuevaLista);
    }
}