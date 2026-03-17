package com.example.mapsretrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador  extends BaseAdapter {
    private Context contexto;
    private List<Ciudad> listaCiudades;
    private Activity mActivity;
    public Adaptador(Context contexto, List<Ciudad> listaCiudades, Activity activity) {
        this.contexto = contexto;
        this.listaCiudades = listaCiudades;
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        return listaCiudades.size();
    }

    @Override
    public Object getItem(int i) {
        return listaCiudades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, final View view, ViewGroup viewGroup) {
        final int pos=i;
        View vista = view;
        String imageUrl;
        LayoutInflater inflador = LayoutInflater.from(contexto);
        vista = inflador.inflate(R.layout.item, null);

        TextView tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
        TextView tvPais = (TextView) vista.findViewById(R.id.tvPais);
        TextView tvPoblacion = (TextView) vista.findViewById(R.id.tvPoblacion);
        ImageView ivCiudad = (ImageView) vista.findViewById(R.id.ivCiudad);
        mActivity.registerForContextMenu(vista);
        ivCiudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });
        tvNombre.setText(listaCiudades.get(i).getName().getCommon());
        tvPais.setText(listaCiudades.get(i).getName().getOfficial());

      //  tvPoblacion.setText("Pobl: "+ listaCiudades.get(i).getPoblacion());
        imageUrl=listaCiudades.get(i).getCoatOfArms().getPng();
        Picasso.get().load(imageUrl).error(R.mipmap.ic_launcher).resize(256, 256).centerInside().into(ivCiudad);


        ImageView ivAmpliar=(ImageView)vista.findViewById(R.id.ivAmpliar);
        ivAmpliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), i+"", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(v.getContext(), AmpliacionImagenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("imagen", listaCiudades.get(i).getCoatOfArms().getPng());
                intent.putExtra("nombre", listaCiudades.get(i).getName().getCommon());
                v.getContext().startActivity(intent);

            }
        });


        ImageView ivMapa=(ImageView)vista.findViewById(R.id.ivMapa);
        ivMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), i+"", Toast.LENGTH_LONG).show();

                //Voy a pasar como parámetros la longitud y la latitud, pero en lugar de hacerlo con un Bundle (que se puede hacer)
                //lo haré a través de las referencias a las variables de clase (es decir, en la clase MapsActivity, he creado unas
                //variables de tipo static, y por ello, puedo referirlas como variables de clase de la forma siguiente
                //Con un Bundle probablamente sea más elegante, pero ambos métodos son igualmente efectivos
                MapsActivity.latitud=listaCiudades.get(i).getLatitud();
                MapsActivity.longitud=listaCiudades.get(i).getLongitud();

                Intent intent=new Intent(v.getContext(), MapsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                //Por otro lado, y por hacerlo diferente de lo que he hecho con la latitud y la longitud (hemos usado variables
                // static o de clase), pasaremos a través de un Bundle el nombre de la ciudad para ponerlo en el marcador
                intent.putExtra("nombre", listaCiudades.get(i).getName().getCommon());
                intent.putExtra("latitud", listaCiudades.get(i).getLatitud());
                intent.putExtra("longitud",listaCiudades.get(i).getLongitud());

                v.getContext().startActivity(intent);
            }
        });


        return vista;
    }
}
