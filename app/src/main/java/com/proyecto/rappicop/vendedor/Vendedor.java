package com.proyecto.rappicop.vendedor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.rappicop.R;

public class Vendedor extends AppCompatActivity {

    Button ofertar;
    Button eliminar;
    public static final String EXTRA_MESSAGE = "mesagge";

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_vendedor);

        ofertar = findViewById(R.id.OfertarComida);
        eliminar = findViewById(R.id.eliminar);

        Intent intent = getIntent();
        String usuario = intent.getStringExtra(EXTRA_MESSAGE);

        ofertar.setOnClickListener(view -> {
            Intent i = new Intent(Vendedor.this, OfertarAlimento.class);
            i.putExtra(OfertarAlimento.EXTRA_MESSAGE, usuario);
            startActivity(i);
        });

        eliminar.setOnClickListener(view -> {
            Intent i = new Intent(Vendedor.this, EliminarOferta.class);
            i.putExtra(OfertarAlimento.EXTRA_MESSAGE, usuario);
            startActivity(i);
        });
    }
}