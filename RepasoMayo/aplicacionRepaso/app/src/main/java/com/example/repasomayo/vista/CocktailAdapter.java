package com.example.repasomayo.vista;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.repasomayo.FavoritiesFragment;
import com.example.repasomayo.R;
import com.example.repasomayo.datos.CocktailDAO;
import com.example.repasomayo.modelo.Cocktail;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.ViewHolder> {

    private float textSize;
    private CocktailDAO dao;
    private Context context;

    List<Cocktail> list;
    public CocktailAdapter(List<Cocktail> list, float textSize, CocktailDAO dao, Context context) {
        this.list=list;
        this.textSize = textSize;
        // DAO inyectado en el constructor del Adapter
        this.dao = dao;
        this.context = context;
    }

    // añadimos un método que permite modificar el tipo de letra en el adaptador
    public void setTextSize(float newSize) {
        this.textSize = newSize;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView fav;
        ImageView image;

        public ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            fav = view.findViewById(R.id.favIcon);
            image = view.findViewById(R.id.image);
        }

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate((R.layout.item_cocktail), parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Cocktail c = list.get(position);


        boolean esFavorito = dao.esFavorito(c.getIdDrink());

        Log.d("ES FAVORITO " , " id: " + c.getIdDrink() +" resultado "+ esFavorito);

        if (esFavorito) {
            holder.fav.setImageResource(R.drawable.favorite_24dp);
        } else {
            holder.fav.setImageResource(R.drawable.favorite_24dp_red);
        }

        holder.name.setText(c.getStrDrink());
        holder.name.setTextSize(textSize);

        holder.fav.setOnClickListener(v -> {

            if (!esFavorito) {
                c.setFavorito( true);
                long insertado =  dao.insertar(c);
                Log.d("ES FAVORITO " , "INSERTADO COMO FAVORITO " + c.getIdDrink());

                holder.fav.setImageResource(R.drawable.favorite_24dp_red);



                notifyDataSetChanged();
                // ACTUALIZAMOS EL OTRO FRAGMENTO
                // Desde Fragment lista , buscas Fragment favoritos directamente
                FragmentActivity activity = (FragmentActivity) context;
                FavoritiesFragment fragment = (FavoritiesFragment)
                        activity.getSupportFragmentManager()
                                .findFragmentById(R.id.bottomFragment);

                if (fragment != null) {
                    fragment.cargarFavoritos();
                }
            }
            else{
                Log.d("ES FAVORITO " , "id "+  c.getIdDrink());
            }
        });


        Glide.with(holder.itemView.getContext())
                .load(c.getStrDrinkThumb())
                .into(holder.image);
    }

    public void setList(ArrayList<Cocktail> lista){
        this.list = lista;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

}
