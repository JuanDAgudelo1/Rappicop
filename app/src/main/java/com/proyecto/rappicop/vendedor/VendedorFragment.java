package com.proyecto.rappicop.vendedor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Usuario;

public class VendedorFragment extends Fragment {

    Button ofertar;
    Button eliminar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View instance = inflater.inflate(R.layout.fragment_vendedor, container, false);
        ofertar = instance.findViewById(R.id.OfertarComida);
        eliminar = instance.findViewById(R.id.eliminar);

        Intent intent = getActivity().getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("user");

        ofertar.setOnClickListener(view -> {
            Intent i = new Intent(instance.getContext(), OfertarAlimento.class);
            i.putExtra("user", user);
            startActivity(i);
        });

        eliminar.setOnClickListener(view -> {
            Intent i = new Intent(instance.getContext(), EliminarOferta.class);
            i.putExtra("user", user);
            startActivity(i);
        });

        return instance;
    }
}