package com.example.ejerciciocopia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView vwReproductor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        vwReproductor=(VideoView)findViewById(R.id.vwReproductor);


        // Obtener el ID del recurso del video desde el Intent
        Intent intent = getIntent();
        int videoResId = intent.getIntExtra("urlVideo", -1);

        // Cargar el recurso del video
        if (videoResId != -1) {

String videoUrl = "https://www.example.com/video.mp4"; // ruta de internet en lugar de ruta local
            Uri uri = Uri.parse(videoUrl);

            //sintaxis: android.resource://<package_name>/<resource_id>
            //String rutaVideo = "android.resource://com.example.ejerciciocopia/" + videoResId;
            /**
             * OTRAS FORMAS
             * String nombreRecurso = getResources().getResourceEntryName(videoResId); // "video1"
             * String tipoRecurso = getResources().getResourceTypeName(videoResId);   // "raw"
             *
             * Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + tipoRecurso + "/" + nombreRecurso);
             */
            //Uri uri = Uri.parse(rutaVideo);

            /**
             * OTRAS FORMAS
             * Uri uri = new Uri.Builder()
             *         .scheme("android.resource")
             *         .authority(getPackageName())
             *         .appendPath(String.valueOf(videoResId))
             *         .build();
             */

            vwReproductor.setVideoURI(uri);
            MediaController mediaController = new MediaController(this);

            mediaController.setAnchorView(vwReproductor);

            vwReproductor.setMediaController(mediaController);


              vwReproductor.setOnPreparedListener(mp -> {
                  Log.d("TAG", "CARGADO");
                  vwReproductor.start();
                               mediaController.show(); // el funcionamiento por defecto del mediacontroller es que aparezca al interactuar con el video
                  // sin embargo, podemos forzar a mostrarlo al comenzar el video
              });

        }
    }
}
