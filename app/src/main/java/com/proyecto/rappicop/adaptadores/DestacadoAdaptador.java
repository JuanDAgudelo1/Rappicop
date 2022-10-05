package com.proyecto.rappicop.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.DestacadoModelo;

import java.util.List;

public class DestacadoAdaptador extends RecyclerView.Adapter<DestacadoAdaptador.ViewHolder>{

    List<DestacadoModelo> list;

    public DestacadoAdaptador(List<DestacadoModelo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.destacado_hori_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imagen.setImageResource(list.get(position).getImagen());
        holder.nombre.setText(list.get(position).getNombre());
        holder.desc.setText(list.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView nombre,desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.destacado_img);
            nombre = itemView.findViewById(R.id.destacado_nombre);
            desc = itemView.findViewById(R.id.destacado_des);

        }
    }
}
