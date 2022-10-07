package com.proyecto.rappicop.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.PopularVerModelo;

import java.util.List;

public class PopularVerAdaptador extends RecyclerView.Adapter<PopularVerAdaptador.ViewHolder> {

    List<PopularVerModelo> list;

    public PopularVerAdaptador(List<PopularVerModelo> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.destacado_ver_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImagen());
        holder.nombre.setText(list.get(position).getNombre());
        holder.descripcion.setText(list.get(position).getDescrpcion());
        holder.calificacion.setText(list.get(position).getCalificacion());
        holder.tiempo.setText(list.get(position).getTiempo());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nombre, descripcion, calificacion, tiempo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.detalle_img);
            nombre = itemView.findViewById(R.id.detalle_nombre);
            descripcion = itemView.findViewById(R.id.detalle_descripcion);
            calificacion = itemView.findViewById(R.id.detalle_calificacion);
            tiempo = itemView.findViewById(R.id.detalle_tiempo);
        }
    }
}