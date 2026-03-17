package com.example.mapsretrofit;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class AmpliacionImagenActivity extends AppCompatActivity {

    private ImageView ivCiudadAmpliada;
    private TextView tvNombreCiudadAmpliada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ampliacion_imagen);
        String imageUrl;
        // ivCiudadAmpliada=(ImageView)findViewById(R.id.ivCiudadAmpliada);
        tvNombreCiudadAmpliada = (TextView) findViewById(R.id.tvNombreCiudadAmpliada);
        ivCiudadAmpliada=findViewById(R.id.ivCiudadAmpliada);
        Bundle bundle = getIntent().getExtras();
        imageUrl = bundle.getString("imagen");
        Picasso.get().load(imageUrl).error(R.mipmap.ic_launcher).
                resize(256, 256).centerInside().into(ivCiudadAmpliada);
        // int datoImagen=bundle.getInt("imagen");
        // ivCiudadAmpliada.setImageResource(datoImagen);
        String datoNombre = bundle.getString("nombre");
        tvNombreCiudadAmpliada.setText(datoNombre);
    }
}