package com.example.repasomayo.modelo;

import java.io.Serializable;

public class Cocktail implements Serializable {

    // HE COMPLETADO MÁS CAMPOS, PARA PODER GUARDAR TODOS LOS DATOS DE CADA ELEMENTO EN LA BBDD
    // PARA PODER PINTARLO
    private int id;              // id local de la bbdd (autoincrement)
    private String idDrink;      // id original de la API
    private String strDrink;
    private String strDrinkThumb;
    private boolean favorito;
    // ESTE ATRIBUTO NO ESTÁ EN LA API


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}