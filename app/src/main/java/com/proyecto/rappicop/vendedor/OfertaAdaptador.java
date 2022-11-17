package com.proyecto.rappicop.vendedor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.ListaElementos;

import java.util.List;
import java.util.Objects;

public class OfertaAdaptador extends RecyclerView.Adapter<OfertaAdaptador.ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private final List<ListaElementos> mData;
    private final LayoutInflater mInflater;
    private final Context context;
    private final boolean eliminarOferta;

    public OfertaAdaptador(List<ListaElementos> itemList, Context context, boolean eliminarOferta) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.eliminarOferta = eliminarOferta;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public OfertaAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_oferta, null);
        view.setOnClickListener(this);
        return new OfertaAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OfertaAdaptador.ViewHolder holder, final int position) {
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
        ImageButton estadorestaurante;

        ViewHolder(View itemView) {
            super(itemView);

            Icono = itemView.findViewById(R.id.logo);
            nombrerestaurante = itemView.findViewById(R.id.nombrerestaurante);
            horario = itemView.findViewById(R.id.precio);
            estadorestaurante = itemView.findViewById(R.id.botoneliminar);
        }

        @SuppressLint("NotifyDataSetChanged")
        void bindData(final ListaElementos item) {
            Icono.setImageBitmap(item.getImg());
            nombrerestaurante.setText(item.getNombrerestaurante());
            horario.setText(item.getHorario());

            estadorestaurante.setVisibility(eliminarOferta ? View.VISIBLE : View.INVISIBLE);
            if (eliminarOferta) {
                estadorestaurante.setOnClickListener(view -> {
                    Logica iu = new Logica(context);

                    AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                    alerta.setMessage("¿Esta seguro de eliminar el producto?")
                            .setCancelable(false)
                            .setPositiveButton("Si", (dialogInterface, i) -> {
                                boolean id = iu.eliminarOferta(item.getVendedor(), item.getNombrerestaurante());

                                if (id) {
                                    Toast.makeText(context, "Se elimino la oferta", Toast.LENGTH_LONG).show();

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        mData.removeIf(n -> (Objects.equals(n.getNombrerestaurante(), item.getNombrerestaurante())));
                                    }

                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "No se elimino", Toast.LENGTH_LONG).show();
                                }

                                dialogInterface.cancel();
                            })
                            .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());

                    AlertDialog alertDialog = alerta.create();
                    alertDialog.setTitle("Eliminar");
                    alertDialog.show();
                });
            }
        }
    }

}
