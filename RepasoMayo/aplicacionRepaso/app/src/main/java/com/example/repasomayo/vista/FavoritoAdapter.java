package com.example.repasomayo.vista;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.activity.result.ActivityResultLauncher;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.repasomayo.DetailActivity;
import com.example.repasomayo.R;
import com.example.repasomayo.datos.CocktailDAO;
import com.example.repasomayo.modelo.Cocktail;

import java.util.List;

public class FavoritoAdapter extends RecyclerView.Adapter<FavoritoAdapter.ViewHolder> {

    private float textSize;
    private CocktailDAO dao;
    private Context context;
    private ActivityResultLauncher<Intent> launcher;

    private List<Cocktail> list;
    private OnCocktailClickListener listener;
    public interface OnCocktailClickListener {
        void detalleCocktail(Cocktail  cocktail);
    }

    public FavoritoAdapter(List<Cocktail> list, float textSize, CocktailDAO dao, OnCocktailClickListener listener) {
        this.list = list;
        this.textSize = textSize;
        // DAO inyectado en el constructor del Adapter
        this.dao = dao;
        this.listener= listener;

    }


    // añadimos un método que permite modificar el tipo de letra en el adaptador
    public void setTextSize(float newSize) {
        this.textSize = newSize;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView fav;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            fav = view.findViewById(R.id.favIcon);
            image = view.findViewById(R.id.image);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.item_cocktail), parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cocktail c = list.get(position);
        holder.name.setText(c.getStrDrink());
        holder.name.setTextSize(textSize);

        holder.itemView.setOnClickListener(v -> {
            /*Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("id", c.getId());
            v.getContext().startActivity(intent);*/
            listener.detalleCocktail(c);


        });
        Glide.with(holder.itemView.getContext()).load(c.getStrDrinkThumb()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
