package com.proyecto.rappicop.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.HomeVertiModelo;

import java.util.ArrayList;
import java.util.List;

public class HomeVertiAdaptador extends RecyclerView.Adapter<HomeVertiAdaptador.ViewHolder> {


    Context context;
    ArrayList<HomeVertiModelo> list;

    public HomeVertiAdaptador(Context context, ArrayList<HomeVertiModelo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,tiempo,calificacion,precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.vertical_img);
            name = itemView.findViewById(R.id.name);
            tiempo = itemView.findViewById(R.id.tiempo);
            calificacion = itemView.findViewById(R.id.calificacion);
            precio = itemView.findViewById(R.id.precio);
        }
    }
}
