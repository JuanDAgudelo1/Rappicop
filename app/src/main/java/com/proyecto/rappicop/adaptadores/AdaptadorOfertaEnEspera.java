package com.proyecto.rappicop.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.DB.Logica;
import com.proyecto.rappicop.NotificationService;
import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.OfertaAceptada;
import com.proyecto.rappicop.modelos.Usuario;

import java.util.ArrayList;

public class AdaptadorOfertaEnEspera extends RecyclerView.Adapter<AdaptadorOfertaEnEspera.ViewHolder> implements View.OnClickListener {

    private View.OnClickListener listener;
    private final ArrayList<OfertaAceptada> listaOfertas;
    private final LayoutInflater mInflater;
    private Context context;
    private Usuario usuario;

    public AdaptadorOfertaEnEspera(ArrayList<OfertaAceptada> listaOfertas, Context context, Usuario usuario) {
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
    public AdaptadorOfertaEnEspera.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_itempedidosespera, null);
        view.setOnClickListener(this);
        return new AdaptadorOfertaEnEspera.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorOfertaEnEspera.ViewHolder holder, final int position) {
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
        Button btnAceptarOfertaEnEspera;

        ViewHolder(View itemView) {
            super(itemView);
            Icono = itemView.findViewById(R.id.Icono);
            nombrerestaurante = itemView.findViewById(R.id.nombreubi);
            horario = itemView.findViewById(R.id.textohorario);
            btnAceptarOfertaEnEspera = itemView.findViewById(R.id.btnAceptarOfertaEnEspera);
        }

        void bindData(final OfertaAceptada item) {
//            Icono.setImageBitmap(item.getImg());
            nombrerestaurante.setText(item.getOferta());
            horario.setText(item.getUbicacion());
            Logica iu = new Logica(context);

            btnAceptarOfertaEnEspera.setOnClickListener(click -> {
                boolean estadoModificado = iu.modificarEstadoOferta(item.getId(), "aceptada", usuario.getUsuario());
                Toast.makeText(context, estadoModificado ? "Oferta aceptada" : "Oferta no aceptada", Toast.LENGTH_SHORT).show();

                if (estadoModificado) {
                    listaOfertas.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), listaOfertas.size());

                    Intent notification = new Intent(context, NotificationService.class);
                    notification.putExtra("title", "Oferta aceptada");
                    notification.putExtra("content", "Oferta de {" + item.getCliente() + "} fue aceptada exitosamente");
                    context.startService(notification);
                }
            });
        }
    }
}