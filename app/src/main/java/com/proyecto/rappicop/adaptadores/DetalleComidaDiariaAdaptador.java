package com.proyecto.rappicop.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.DetalleComidaDiariaModelo;

import java.util.List;

public class DetalleComidaDiariaAdaptador extends RecyclerView.Adapter<DetalleComidaDiariaAdaptador.ViewHolder> {

    List<DetalleComidaDiariaModelo> list;

    public DetalleComidaDiariaAdaptador(List<DetalleComidaDiariaModelo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalle_comida_diaria_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.nombre.setText(list.get(position).getName());
        holder.precio.setText(list.get(position).getPrecio());
        holder.descripcion.setText(list.get(position).getDescripcion());
        holder.tiempo.setText(list.get(position).getTiempo());
        holder.calificacion.setText(list.get(position).getCalificacion());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nombre,precio,descripcion,calificacion,tiempo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.detalle_img);
            calificacion=itemView.findViewById(R.id.detalle_calificacion);
            descripcion=itemView.findViewById(R.id.detalle_descripcion);
            tiempo=itemView.findViewById(R.id.detalle_tiempo);
            nombre=itemView.findViewById(R.id.detalle_nombre);
            precio=itemView.findViewById(R.id.detalle_precio);
        }
    }
}


