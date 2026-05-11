package com.example.repasomayo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;


import com.example.repasomayo.datos.CocktailDAO;
import com.example.repasomayo.modelo.ApiService;
import com.example.repasomayo.modelo.Cocktail;
import com.example.repasomayo.modelo.CocktailResponse;
import com.example.repasomayo.vista.CocktailAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 */
public class CocktailListFragment extends Fragment {
    private final String URL = "https://www.thecocktaildb.com/api/json/v1/1/";
    private RecyclerView recycler;
    private CocktailAdapter adapter;
    private Context context;
    private List<Cocktail> lista;

    // TODO: Customize parameter argument names
    private CocktailDAO dao;
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CocktailListFragment() {
        super(R.layout.fragment_list);

    }



    public static CocktailListFragment newInstance(CocktailDAO dao) {
        CocktailListFragment fragment = new CocktailListFragment();
        fragment.dao = dao;
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        context = requireContext();
        cargarDatos();



    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void updateTextSize(float size) {
        if (adapter != null) {
            adapter.setTextSize(size);
        }
    }


    private void cargarDatos() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);

        api.getCocktails().enqueue(new Callback<CocktailResponse>() {
            @Override
            public void onResponse(Call<CocktailResponse> call, Response<CocktailResponse> response) {

                List<Cocktail> list = response.body().drinks;


                SharedPreferences prefs = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
                float size = prefs.getFloat("textSize", 16f);
                // VAMOS A CREAR LA VARIABLE PARA EL ADAPTER
                adapter = new CocktailAdapter(list, size,dao,context);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CocktailResponse> call, Throwable t) {
                Log.d("REVISIÓN","ERROR AL CARGAR DATOS");


            }
        });
    }

}