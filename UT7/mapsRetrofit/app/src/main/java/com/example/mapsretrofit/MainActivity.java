package com.example.mapsretrofit;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Adaptador miAdaptador;
    private ListView listaCiudades;
    ArrayList<Ciudad> lista;
    int posicion;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCiudades = findViewById(R.id.lvPaises);
        context = this;

        lista = realizarPeticion_Retrofit();
        // https://restcountries.com/v3.1/name/


    }

    //Métodos para MENÚ CONTEXTUAL
    //Tenemos que sobreescribir los siguientes 2 métodos para el menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Elija qué desea hacer:");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() ==R.id.itemAmpliarImagen){
                //ampliar(posicion);
                Toast.makeText(getApplicationContext(), "Ampliando", Toast.LENGTH_LONG).show();
                }
        else if(item.getItemId() ==  R.id.itemMapa){
            //mostrarMapa(posicion);
            /*    Intent i = new Intent(this, MapsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("latitud", lista.get(posicion).getLatitud());
                extras.putString("longitud", lista.get(posicion).getLongitud());
                extras.putString("nombre",lista.get(posicion).getNombre() );
                i.putExtras(extras);
                startActivity(i);*/
            Toast.makeText(getApplicationContext(), "Mostrando mapa", Toast.LENGTH_LONG).show();
        }

        return true;
    }
    private ArrayList<Ciudad> realizarPeticion_Retrofit(){
        // instancia de Retrofit
        // hemos creado la interfaz para el servicio "Servicio" en la que está definida la ruta
        lista= new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/v3.1/name/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicio service = retrofit.create(Servicio.class);

        Call<List<Ciudad>> call = service.getUserPosts("https://restcountries.com/v3.1/name/"+"it");
        call.enqueue(new Callback<List<Ciudad>>() {

            @Override
            public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                if (!response.isSuccessful()) {
                    Log.i("TAG", "Error" + response.code());
                } else {
                    List<Ciudad>resultados = response.body();
                    lista= new ArrayList();
                    Iterator<Ciudad>  it = resultados.iterator();
                    while(it.hasNext()){
                        lista.add(it.next());
                    }
                    System.out.println();
                    miAdaptador = new Adaptador(context, lista, MainActivity.this);

                    listaCiudades.setAdapter(miAdaptador);
                    listaCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //registerForContextMenu(view);
                            posicion = position;
                            Log.i("CLICK","ittem");
                            Toast.makeText(getApplicationContext(), position + "", Toast.LENGTH_LONG).show();

                        }
                    });


                    listaCiudades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                            registerForContextMenu(view);
                            //posicion = position; //De esta forma tengo determinado sobre que item del Listview estoy actuando
                            //Toast.makeText(getApplicationContext(), position, Toast.LENGTH_LONG).show();

                            return false;
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<List<Ciudad>> call, Throwable t) {
                // Manejar el fallo de la llamada aquí
            }
        });


        return lista;
    }


}