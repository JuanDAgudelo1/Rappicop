package com.proyecto.rappicop.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.rappicop.R;
import com.proyecto.rappicop.modelos.HomeHoriModelo;
import com.proyecto.rappicop.modelos.HomeVertiModelo;

import java.util.ArrayList;
import java.util.List;

public class HomeHoriAdaptador extends RecyclerView.Adapter<HomeHoriAdaptador.ViewHolder>{

    UpdateVertical updateVertical;
    Activity activity;
    ArrayList<HomeHoriModelo> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHoriAdaptador(UpdateVertical updateVertical, Activity activity, ArrayList<HomeHoriModelo> list) {
        this.updateVertical = updateVertical;
        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if (check) {
            ArrayList<HomeVertiModelo> homeVertiModelos = new ArrayList<>();
            homeVertiModelos.add(new HomeVertiModelo(R.drawable.pizza1, "Pizza 1", "10:00 - 23:00", "4.9", "Min - $15.000"));
            homeVertiModelos.add(new HomeVertiModelo(R.drawable.pizza2, "Pizza 2", "10:00 - 23:00", "4.9", "Min - $15.000"));
            homeVertiModelos.add(new HomeVertiModelo(R.drawable.pizza3, "Pizza 3", "10:00 - 23:00", "4.9", "Min - $15.000"));
            homeVertiModelos.add(new HomeVertiModelo(R.drawable.pizza4, "Pizza 4", "10:00 - 23:00", "4.9", "Min - $15.000"));

            updateVertical.callback(position, homeVertiModelos);
            check = false;
        }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row_index = position;
                    notifyDataSetChanged();

                    if (position == 0){


                        ArrayList<HomeVertiModelo> homeVertiModelos = new ArrayList<>();
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.pizza1,"Pizza 1","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.pizza2,"Pizza 2","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.pizza3,"Pizza 3","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.pizza4,"Pizza 4","10:00 - 23:00","4.9","Min - $15.000"));

                        updateVertical.callback(position, homeVertiModelos);
                    }
                    else  if (position == 1){
                        ArrayList<HomeVertiModelo> homeVertiModelos = new ArrayList<>();

                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.hamburguesa1,"Hamburgesa 1","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.hamburguesa2,"Hamburgesa 2","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.hamburgesa3,"Hamburgesa 3","10:00 - 23:00","4.9","Min - $15.000"));

                        updateVertical.callback(position,homeVertiModelos);
                    }

                    else  if (position == 2){
                        ArrayList<HomeVertiModelo> homeVertiModelos = new ArrayList<>();

                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.papa1,"Papa Francesa 1","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.papa2,"Papa Francesa 2","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.papa3,"Papa Francesa 3","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.papa4,"Papa Francesa 4","10:00 - 23:00","4.9","Min - $15.000"));

                        updateVertical.callback(position,homeVertiModelos);
                    }

                    else  if (position == 3){
                        ArrayList<HomeVertiModelo> homeVertiModelos = new ArrayList<>();

                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.helado1,"Helado 1","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.helado2,"Helado 2","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.helado3,"Helado 3","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.helado4,"Helado 4","10:00 - 23:00","4.9","Min - $15.000"));
                        updateVertical.callback(position,homeVertiModelos);
                    }

                    else  if (position == 4){
                        ArrayList<HomeVertiModelo> homeVertiModelos = new ArrayList<>();

                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.sandwich1,"Sandwich 1","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.sandwich2,"Sandwich 2","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.sandwich3,"Sandwich 3","10:00 - 23:00","4.9","Min - $15.000"));
                        homeVertiModelos.add(new HomeVertiModelo(R.drawable.sandwich4,"Sandwich 4","10:00 - 23:00","4.9","Min - $15.000"));

                        updateVertical.callback(position,homeVertiModelos);
                    }
                }
            });

            if (select){
                if (position == 0){
                    holder.cardView.setBackgroundResource(R.drawable.cambio_bg);
                    select = false;
                }
            }
            else {
                if (row_index == position){
                    holder.cardView.setBackgroundResource(R.drawable.cambio_bg);
                } else{
                    holder.cardView.setBackgroundResource(R.drawable.pordefecto_bg);

                }
            }
        }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.hori_img);
            name = itemView.findViewById(R.id.hori_texto);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
