package com.proyecto.rappicop.ui.carrocompras;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.actividades.OfertaConsumidor;
import com.proyecto.rappicop.adaptadores.AdaptadorProducto;
import com.proyecto.rappicop.modelos.CarritoModelo;
import com.proyecto.rappicop.modelos.ListaCarritoModelo;
import com.proyecto.rappicop.modelos.Oferta;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CarroComprasFragment extends Fragment {

    RecyclerView recyclerView;

    private LinearLayout lyConProductos, lySinProductos;
    private Logica iu;
    Usuario usuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        iu = new Logica(getActivity());

        View view = inflater.inflate(R.layout.fragment_tu_carrito, container, false);
        lyConProductos = view.findViewById(R.id.layoutCarroComprasConProductos);
        lySinProductos = view.findViewById(R.id.layoutCarroComprasSinProductos);

        Button boton = view.findViewById(R.id.iroferta);
        recyclerView = view.findViewById(R.id.listadeproductos);

        Intent intent = getActivity().getIntent();
        usuario = (Usuario) intent.getSerializableExtra("user");

        boton.setOnClickListener(viewOferta -> {
            Intent i = new Intent(getActivity(), OfertaConsumidor.class);
            i.putExtra("user", usuario);
            startActivity(i);
        });

        return view;
    }

    public void init(ArrayList<CarritoModelo> listacarrito) {
        List<ListaCarritoModelo> elements = new ArrayList<>();
        Logica iu = new Logica(getActivity());

        for (CarritoModelo x : listacarrito) {
            Oferta oferta = iu.consultaOfertaPorNombre(x.getProducto());
            Bitmap bim = oferta.getImagen() == null ? null : BitmapFactory.decodeByteArray(oferta.getImagen(), 0, oferta.getImagen().length);
            elements.add(new ListaCarritoModelo(bim, oferta.getNombre(), "$" + oferta.getPrecio(), oferta.getDescripcion(), "" + x.getCantidad(), x.getConsumidor(), x.getVendedor()));
        }

        AdaptadorProducto adaptadorProducto = new AdaptadorProducto(elements, getActivity(), usuario);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adaptadorProducto);
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<CarritoModelo> listacarrito = iu.consultaCarroPorUsuario(usuario.getUsuario());
        boolean isEmpty = listacarrito.isEmpty();

        if (!isEmpty) {
            init(listacarrito);
        }

        lyConProductos.setVisibility(!isEmpty ? View.VISIBLE : View.INVISIBLE);
        lySinProductos.setVisibility(isEmpty ? View.VISIBLE : View.INVISIBLE);
    }
}