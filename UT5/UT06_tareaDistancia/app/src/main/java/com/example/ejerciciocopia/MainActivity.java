package com.example.ejerciciocopia;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ejerciciocopia.model.DAOelementos;
import com.example.ejerciciocopia.model.Elemento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    // Almacena las imágenes guardadas localmente


    private MediaPlayer mediaPlayer;
    private Elemento elementos[];
    private TransitionDrawable transition;
    private ConstraintLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // en este código, la transición  está en el layout principal, aunque tb se prodría hacer en cualquier elemento View
         mainLayout = findViewById(R.id.main);
       // TransitionDrawable transition = (TransitionDrawable) getResources().getDrawable(R.drawable.bg_transition);
         transition = (TransitionDrawable) ContextCompat.getDrawable(this, R.drawable.bg_transition);
        mainLayout.setBackground(transition);


        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);

        elementos = DAOelementos.getElementos();

        // Configurar el GridView y asignar el adaptador personalizado
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(this, elementos));
        // Listener para ítems del menú
        topAppBar.setOnMenuItemClickListener(item -> {
            if(item.getItemId()== R.id.fav){
                // acción  pantalla de reproducción de enlaces
                Intent intent = new Intent(this, AudioEnlacesActivity.class );
                //no necesito extras en el intent
                startActivity(intent);

                return true;
            }else if (item.getItemId()==R.id.search){
                // acción search
                return true;
            }

            return false;
        });
        // Acción icono navegación
        topAppBar.setNavigationOnClickListener(v -> {
            // Aquí puedes abrir un drawer o navegar atrás
            Toast.makeText(this, "Menú pulsado", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (transition != null) {
            transition.startTransition(8000); // 8 segundos
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar el MediaPlayer si está activo
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * Si tienes una clase principal y una funcionalidad que solo tiene sentido en el contexto de esa clase, la puedes implementar como una clase anidada.
     * Esto mantiene el código más organizado y evita que clases auxiliares relacionadas "contaminen" el espacio de nombres general.
     * CUIDADO CON Fugas de memoria: Las clases internas no estáticas pueden mantener referencias implícitas a la clase externa, lo que puede causar fugas de memoria si no se manejan correctamente.
        usamos MediaPlayer de la clase externa para controlar el sonido
     */
    class GridAdapter extends BaseAdapter {
        Context context;

        Elemento[] elementos;

        // Una clase anidada (especialmente una clase interna no estática) tiene acceso directo a los miembros (incluso privados) de la clase externa.

        // Constructor para inicializar los datos
        GridAdapter(Context context, Elemento elementos[]) {
            this.context = context;
            this.elementos = elementos;

        }

        @Override
        public int getCount() {
            return elementos.length; // Número total de elementos
        }

        @Override
        public Object getItem(int position) {
            return elementos[position];
        }

        @Override
        public long getItemId(int position) {
            return elementos[position].getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Inflar el diseño  si no se ha reutilizado
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
            }


            ImageView imageView = convertView.findViewById(R.id.imageView);
            ImageButton btnPlayAudio = convertView.findViewById(R.id.btnPlayAudio);
            ImageButton btnStopAudio = convertView.findViewById(R.id.btnStopAudio);

            // Cargar la imagen desde los recursos locales
            imageView.setImageResource(elementos[position].getImagen());

            // reproducir el video con un VideoView en otro activity
            imageView.setOnClickListener(v -> {
                int url = elementos[position].getVideo(); //
                Intent intent = new Intent(context, VideoActivity.class );
                intent.putExtra("urlVideo", url);
                context.startActivity(intent);
            });

            // Reproducir el sonido asociado al botón de reproducción
            btnPlayAudio.setOnClickListener(v -> playAudio(elementos[position].getAudio()));

            // Detener el sonido al presionar el botón de detener
            btnStopAudio.setOnClickListener(v -> stopAudio());

            return convertView;
        }

        // Reproduce el sonido asociado a un recurso
        private void playAudio(int audioResId) {
            // Detener cualquier reproducción en curso
            stopAudio();

            // Crear un nuevo MediaPlayer y reproducir el sonido
            mediaPlayer = MediaPlayer.create(context, audioResId);
            mediaPlayer.start();

            // Liberar el MediaPlayer automáticamente cuando finalice
            mediaPlayer.setOnCompletionListener(mp -> {
                mp.release();
                mediaPlayer = null;
            });
        }

        // Detiene el sonido si se está reproduciendo
        private void stopAudio() {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    // Aunque no es estrictamente necesario llamar a isPlaying() antes de stop(),
                    // hacerlo evita posibles errores si el MediaPlayer no está reproduciendo.
                    mediaPlayer.stop(); // Detener la reproducción
                    // Detiene la reproducción del audio en curso. Una vez que llamas a stop(),
                    // el MediaPlayer no puede ser reutilizado para reproducir el mismo audio sin configurarlo nuevamente.
                }
                mediaPlayer.release(); // Liberar los recursos del MediaPlayer
                // Esto es esencial para evitar fugas de memoria, especialmente si el MediaPlayer se crea varias veces
                // durante el ciclo de vida de tu aplicación.
                mediaPlayer = null; // Limpiar la referencia
                // Limpia la referencia al objeto,
                // asegurándote de que no se intente usar un MediaPlayer ya liberado.
            }
        }
    }
}
