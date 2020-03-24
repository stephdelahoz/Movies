package com.example.Movies;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterActivity extends RecyclerView.Adapter<AdapterActivity.ViewHolderP> {

    private ArrayList<Pelicula> pelis;

    public AdapterActivity(ArrayList<Pelicula> lpelis) {
        this.pelis = lpelis;
    }

    @NonNull
    @Override
    public AdapterActivity.ViewHolderP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, null, false);
        return new ViewHolderP(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterActivity.ViewHolderP holder, int position) {
        holder.txtNombre.setText(pelis.get(position).getNombre());
        holder.txtDirector.setText(pelis.get(position).getDirector());
        holder.txtGenero.setText(pelis.get(position).getGenero());
    }

    @Override
    public int getItemCount() {
        return pelis.size();
    }

    public class ViewHolderP extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView txtNombre, txtDirector, txtGenero;

        public ViewHolderP(View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.tvnombre);
            txtDirector = itemView.findViewById(R.id.tvdirector);
            txtGenero = itemView.findViewById(R.id.tvgenero);
            itemView.setOnCreateContextMenuListener(this);
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }
}
