package com.proyecto.rappicop.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.OfertaAceptada;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;

public class AdaptadorOfertaAceptada extends RecyclerView.Adapter<AdaptadorOfertaAceptada.ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private final ArrayList<OfertaAceptada> listaOfertas;
    private final LayoutInflater mInflater;
    private final Context context;
    private final Usuario usuario;

    public AdaptadorOfertaAceptada(ArrayList<OfertaAceptada> listaOfertas, Context context, Usuario usuario) {
        this.mInflater = LayoutInflater.from(context);
        this.listaOfertas = listaOfertas;
        this.context = context;
        this.usuario = usuario;
    }

    @Override
    public int getItemCount() {
        return listaOfertas.size();
    }

    @Override
    public AdaptadorOfertaAceptada.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos, null);
        view.setOnClickListener(this);
        return new AdaptadorOfertaAceptada.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorOfertaAceptada.ViewHolder holder, final int position) {
        holder.bindData(listaOfertas.get(position));
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

        ImageView Icono;
        TextView nombrerestaurante, horario;
        Button estadoOferta;

        ViewHolder(View itemView) {
            super(itemView);

            Icono = itemView.findViewById(R.id.Icono);
            nombrerestaurante = itemView.findViewById(R.id.nombrerestaurante);
            horario = itemView.findViewById(R.id.textohorario);
            estadoOferta = itemView.findViewById(R.id.estadoOferta);
        }

        void bindData(final OfertaAceptada item) {
//            Icono.setImageBitmap(item.getImg());
            nombrerestaurante.setText(item.getOferta());
            horario.setText(item.getUbicacion());
            estadoOferta.setText(item.getEstado());
        }
    }
}