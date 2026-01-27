package com.example.ejerciciocopia.model;

public class Elemento {
    private  int id;
    private static int contador;
    private int imagen;
    private int audio;
    private int video;

    public Elemento(int imagen, int audio, int video) {
        this.id = contador++;
        this.imagen = imagen;
        this.audio = audio;
        this.video = video;
    }

    // getters y setters

    public int getImagen() {
        return imagen;
    }

    public int getAudio() {
        return audio;
    }

    public int getVideo() {
        return video;
    }
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    public void setVideo(int video) {
        this.video = video;}

    public int getId() {
        return id;
    }


}
