package com.example.repasomayo;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.repasomayo.datos.CocktailDAO;
import com.example.repasomayo.modelo.Cocktail;
import com.example.repasomayo.vista.FavoritoAdapter;

import java.util.List;


public class FavoritiesFragment extends Fragment implements FavoritoAdapter.OnCocktailClickListener {

    private RecyclerView recycler;
    private CocktailDAO dao;
    private ActivityResultLauncher<Intent> launcher;
    private FavoritoAdapter adapter;
    private List<Cocktail> list;

    public FavoritiesFragment(){

        super (R.layout.fragment_favorites);
    }

    public static FavoritiesFragment newInstance(CocktailDAO dao) {
        FavoritiesFragment fragment = new FavoritiesFragment();
        fragment.dao=dao;

        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        recycler = view.findViewById(R.id.recyclerFav);
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){

                            Intent data = result.getData();
                            if (data != null) {


                                int id =data.getIntExtra("id",-1);
                                // borrar del arraylist el id

                                for (int i = 0; i < list.size(); i++) {

                                    if (list.get(i).getId() == id) {

                                        list.remove(i);

                                        adapter.notifyItemRemoved(i);

                                        break;
                                    }
                                }
                            }

                            // eliminar el cocktail de la lista actual // ya está eliminado


                        }
                    }
                }
        );
        cargarFavoritos();
    }

    public void cargarFavoritos(){

        SharedPreferences prefs = getContext().getSharedPreferences("settings", getContext().MODE_PRIVATE);
        float size = prefs.getFloat("textSize", 16f);
        list = dao.obtenerFavoritos();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoritoAdapter(list, size,dao,this);
        recycler.setAdapter(adapter);

    }

    @Override
    public void onResume(){
        super.onResume();
        cargarFavoritos();
    }

    @Override
    public void detalleCocktail(Cocktail cocktail) {
        Intent i =
                new Intent(requireActivity(), DetailActivity.class);

        i.putExtra("id", cocktail.getId());
        // el id de la base de datos
        launcher.launch(i);

    }




}