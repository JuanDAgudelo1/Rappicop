package com.proyecto.rappicop.ui.carrito;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.proyecto.rappicop.R;

public class TuCarritoFragment extends Fragment {

    public TuCarritoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tu_carrito, container, false);
    }
}