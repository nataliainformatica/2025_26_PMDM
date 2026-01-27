package com.example.ejerciciocopia.model;

import com.example.ejerciciocopia.R;

public class DAOelementos {
    static Elemento[] elementos;

    public static Elemento[] getElementos() {
        if (elementos == null) {
            elementos = new Elemento[7];
            int[] imagenes = {
                    R.drawable.image1,
                    R.drawable.image2 ,
                    R.drawable.image3,
                    R.drawable.image4,
                    R.drawable.image5,
                    R.drawable.image7,
                    R.drawable.image7
            };

            //  los audios almacenados localmente
            int[] audios = {
                    // para que la app  no sea muy pesada utilizamos el mismo audio
                    R.raw.audio1,
                    R.raw.audio1,
                    R.raw.audio1,
                    R.raw.audio1,
                    R.raw.audio1,
                    R.raw.audio1,
                    R.raw.audio1,
            };

            //  videos
            int[] videos = {
                    // para que la app  no sea muy pesada utilizamos el mismo video
                    R.raw.video1,
                    R.raw.video1,
                    R.raw.video1,
                    R.raw.video1,
                    R.raw.video1,
                    R.raw.video1,
                    R.raw.video1,


            };
            for (int i = 0; i < elementos.length; i++) {
                elementos[i]= new Elemento(imagenes[i], audios[i], videos[i]);
            }
        }
        return elementos;
    }
}
