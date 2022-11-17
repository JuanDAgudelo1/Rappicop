package com.proyecto.rappicop.actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.ListaElementos;

import java.util.List;

public class ListaAdaptador extends RecyclerView.Adapter<ListaAdaptador.ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private final List<ListaElementos> mData;
    private final LayoutInflater mInflater;
    private final Context context;
    private final String user;

    public ListaAdaptador(List<ListaElementos> itemList, Context context, String user) {
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
    public ListaAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos, null);
        view.setOnClickListener(this);
        return new ListaAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListaAdaptador.ViewHolder holder, final int position) {
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
        ImageView Icono;
        TextView nombrerestaurante, horario;
        Button estadorestaurante;

        ViewHolder(View itemView) {
            super(itemView);

            Icono = itemView.findViewById(R.id.Icono);
            nombrerestaurante = itemView.findViewById(R.id.nombrerestaurante);
            horario = itemView.findViewById(R.id.textohorario);
            estadorestaurante = itemView.findViewById(R.id.estadorestaurante);
        }

        void bindData(final ListaElementos item) {
            Icono.setImageBitmap(item.getImg());
            nombrerestaurante.setText(item.getNombrerestaurante());
            horario.setText(item.getHorario());

            estadorestaurante.setOnClickListener(view -> {
                Logica iu = new Logica(context);

                if (iu.verificarCarrito(user, item.getVendedor(), item.getNombrerestaurante())) {
                    boolean aumentarCarro = iu.aumentarCantidadCarrito(user, item.getVendedor(), item.getNombrerestaurante());
                    Toast.makeText(context, aumentarCarro ? "Aumenta cantidad" : "No aumenta", Toast.LENGTH_LONG).show();
                } else {
                    long id = iu.nuevoCarrito(user, item.getVendedor(), item.getNombrerestaurante(), 1);
                    Toast.makeText(context, id > 0 ? "Agregado" : "No fue agregada", Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
