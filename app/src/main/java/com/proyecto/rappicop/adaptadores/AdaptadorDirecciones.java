package com.proyecto.rappicop.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.domiciliario.AceptarOferta;
import com.proyecto.rappicop.modelos.CarritoModelo;
import com.proyecto.rappicop.modelos.Direccion;
import com.proyecto.rappicop.modelos.ListaCarritoModelo;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorDirecciones extends RecyclerView.Adapter<AdaptadorDirecciones.ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private final List<Direccion> mData;
    private final LayoutInflater mInflater;
    private final Context context;
    private final Usuario usuario;
    private final ListaCarritoModelo listaCarritoModelo;

    public AdaptadorDirecciones(List<Direccion> itemList, Context context, Usuario usuario, ListaCarritoModelo listaCarritoModelo) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.usuario = usuario;
        this.listaCarritoModelo = listaCarritoModelo;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public AdaptadorDirecciones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_direccion, null);
        view.setOnClickListener(this);
        return new AdaptadorDirecciones.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorDirecciones.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, direccion;

        ViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreubi);
            direccion = itemView.findViewById(R.id.descripciondireccion);
        }

        void bindData(final Direccion item) {
            nombre.setText(item.getNombre());
            direccion.setText(item.getDireccion());

            itemView.setOnClickListener(click -> {
                Intent i = new Intent(context, AceptarOferta.class);
                i.putExtra("user", usuario);
                i.putExtra("direccion", item);
                i.putExtra("carro", listaCarritoModelo);
                context.startActivity(i);
            });
        }

    }
}

