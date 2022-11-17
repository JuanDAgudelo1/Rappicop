package com.proyecto.rappicop.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.Direccion;

import java.util.List;

public class AdaptadorDirecciones extends RecyclerView.Adapter<AdaptadorDirecciones.ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<Direccion> mData;
    private LayoutInflater mInflater;
    private Context context;
    private String user;

    public AdaptadorDirecciones(List<Direccion> itemList, Context context, String user) {
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
    public AdaptadorDirecciones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_direccion, null);
        view.setOnClickListener(this);
        return new AdaptadorDirecciones.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final AdaptadorDirecciones.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Direccion> items) {
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
        TextView nombre, direccion;

        ViewHolder(View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreubi);
            direccion = itemView.findViewById(R.id.descripciondireccion);
        }

        void bindData(final Direccion item) {
            nombre.setText(item.getNombre());
            direccion.setText(item.getDireccion());
        }

    }
}

