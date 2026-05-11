package com.example.repasomayo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.repasomayo.datos.CocktailDAO;
import com.example.repasomayo.modelo.Cocktail;
import com.example.repasomayo.vista.FavoritoAdapter;

public class MainActivity extends AppCompatActivity  {
    private CocktailDAO cocktailDAO;
    private CocktailListFragment cl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        cocktailDAO = new CocktailDAO(this); // una sola instancia
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         cl= CocktailListFragment.newInstance(cocktailDAO);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.topframgment,  cl)
                .replace(R.id.bottomFragment, FavoritiesFragment.newInstance(cocktailDAO))
                .commit();


        SharedPreferences prefs = getSharedPreferences ("settings", MODE_PRIVATE);
        float size = prefs.getFloat("textSize", 16f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if ( item.getItemId()==R.id.changeText ){
            SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);

            float current = prefs.getFloat("textSize", 16f);

            float nuevo;

            if (current == 16f) {
                nuevo = 24f;
            } else {
                nuevo = 16f;
            }

            prefs.edit ().putFloat("textSize", nuevo).apply();

             // VAMOS A HACER UNA ALTERNATIVA a LLAMAR A RECREATE

            //recreate();

            CocktailListFragment fragment =
                    (CocktailListFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.topframgment);

            if (fragment != null) {
                fragment.updateTextSize(nuevo);
            }



        }
        else if(item.getItemId()==R.id.finish){
            finish();

        }
        return true;
    }



}