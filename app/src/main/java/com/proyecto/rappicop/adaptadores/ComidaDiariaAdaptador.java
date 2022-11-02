package com.proyecto.rappicop.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.actividades.DetalleComidiaDiariaActivity;
import com.proyecto.rappicop.modelos.ComidaDiariaModelo;

import java.util.List;

public class ComidaDiariaAdaptador extends RecyclerView.Adapter<ComidaDiariaAdaptador.ViewHolder> {

    Context context;
    List<ComidaDiariaModelo> list;

    public ComidaDiariaAdaptador(Context context, List<ComidaDiariaModelo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comida_diaria, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.descuento.setText(list.get(position).getDescuento());
        holder.descripcion.setText(list.get(position).getDescripcion());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetalleComidiaDiariaActivity.class);
            intent.putExtra("tipo", list.get(position).getTipo());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, descripcion, descuento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.textView9);
            descripcion = itemView.findViewById(R.id.textView10);
            descuento = itemView.findViewById(R.id.descuento);
        }
    }
}
