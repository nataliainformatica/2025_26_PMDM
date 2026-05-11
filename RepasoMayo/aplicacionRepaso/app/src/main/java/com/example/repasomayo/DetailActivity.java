package com.example.repasomayo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.repasomayo.datos.CocktailDAO;

public class DetailActivity extends AppCompatActivity {
    private CocktailDAO cocktailDAO;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        cocktailDAO = new CocktailDAO(this);

        Button btn = findViewById(R.id.deleteBtn);
        // RECUPERAMOS EL VALOR DEL INTENT
         id = getIntent().getIntExtra("id", -1);




        btn.setOnClickListener(v->{
            try{
                int resultaod= cocktailDAO.eliminar(String.valueOf(id));
                Intent data = new Intent();
                data.putExtra("id", id);
                setResult(RESULT_OK, data);
            } catch (Exception e) {
               Log.d("REVISAR", "id no es correcto");
            }

            finish();
        });

    }
}