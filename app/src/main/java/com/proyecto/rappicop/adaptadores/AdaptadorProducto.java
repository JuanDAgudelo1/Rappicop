package com.proyecto.rappicop.adaptadores;

import android.content.Context;
import android.os.Build;
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
import com.proyecto.rappicop.modelos.ListaCarritoModelo;

import java.util.List;
import java.util.Objects;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolder> {

    private final List<ListaCarritoModelo> mData;
    private final LayoutInflater mInflater;
    private final Context context;

    public AdaptadorProducto(List<ListaCarritoModelo> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_producto, null);
        return new AdaptadorProducto.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Icono;
        TextView nproducto, precio, descripcion, cantidad;
        Button mas, menos;

        ViewHolder(View itemView) {
            super(itemView);
            Icono = itemView.findViewById(R.id.icono);
            nproducto = itemView.findViewById(R.id.item_nombre);
            precio = itemView.findViewById(R.id.item_precio);
            descripcion = itemView.findViewById(R.id.item_descripcion);
            cantidad = itemView.findViewById(R.id.contadorproducto);
            mas = itemView.findViewById(R.id.Buttonplus);
            menos = itemView.findViewById(R.id.Buttonminus);
        }

        void bindData(final ListaCarritoModelo item) {
            Icono.setImageBitmap(item.getImg());
            nproducto.setText(item.getnproducto());
            precio.setText(item.getprecio());
            descripcion.setText(item.getdescripcion());
            cantidad.setText(item.getCantidad());

            Logica iu = new Logica(context);

            mas.setOnClickListener(view -> {
                if (iu.verificarCarrito(item.getConsumidor(), item.getVendedor(), item.getnproducto())) {
                    if (iu.aumentarCantidadCarrito(item.getConsumidor(), item.getVendedor(), item.getnproducto())) {
                        int sum = Integer.parseInt(cantidad.getText().toString()) + 1;
                        cantidad.setText(String.valueOf(sum));
                    } else {
                        Toast.makeText(context, "No aumenta", Toast.LENGTH_LONG).show();
                    }
                }
            });

            menos.setOnClickListener(view -> {
                if (iu.verificarCarrito(item.getConsumidor(), item.getVendedor(), item.getnproducto())) {
                    if (iu.disminuirCantidadCarrito(item.getConsumidor(), item.getVendedor(), item.getnproducto())) {
                        int men = Integer.parseInt(cantidad.getText().toString()) - 1;
                        if (men <= 0) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                mData.removeIf(n -> (Objects.equals(n.getnproducto(), item.getnproducto())));
                            }

                            notifyDataSetChanged();
                        }

                        cantidad.setText(String.valueOf(men));
                    } else {
                        Toast.makeText(context, "No disminuye", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}

