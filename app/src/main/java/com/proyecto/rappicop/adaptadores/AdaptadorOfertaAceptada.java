package com.proyecto.rappicop.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.ListaElementos;

import java.util.List;

public class AdaptadorOfertaAceptada extends RecyclerView.Adapter<AdaptadorOfertaAceptada.ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<ListaElementos> mData;
    private LayoutInflater mInflater;
    private Context context;
    private String user;

    public AdaptadorOfertaAceptada(List<ListaElementos> itemList, Context context, String user) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.user = user;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public AdaptadorOfertaAceptada.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos, null);
        view.setOnClickListener(this);
        return new AdaptadorOfertaAceptada.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorOfertaAceptada.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementos> items) {
        mData = items;
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
        TextView nombrerestaurante, horario, estadorestaurante;

        ViewHolder(View itemView) {
            super(itemView);
            Icono = itemView.findViewById(R.id.Icono);
            nombrerestaurante = itemView.findViewById(R.id.nombreubi);
            horario = itemView.findViewById(R.id.textohorario);
            estadorestaurante = itemView.findViewById(R.id.estadorestaurante);
        }

        void bindData(final ListaElementos item) {
            Icono.setImageBitmap(item.getImg());
            nombrerestaurante.setText(item.getNombrerestaurante());
            horario.setText(item.getHorario());
            estadorestaurante.setText(item.getEstado());
        }
    }
}

