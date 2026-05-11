package com.example.repasomayo.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.repasomayo.modelo.Cocktail;

import java.util.ArrayList;
import java.util.List;

public class CocktailDAO {

    public static final String TABLE_NAME = "cocktails";

    private static final String COL_ID             = "id";
    private static final String COL_ID_DRINK       = "idDrink";
    private static final String COL_STR_DRINK      = "strDrink";
    private static final String COL_STR_THUMB      = "strDrinkThumb";
    private static final String COL_FAVORITO       = "favorito";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ID_DRINK   + " TEXT UNIQUE NOT NULL, " +
                    COL_STR_DRINK  + " TEXT NOT NULL, " +
                    COL_STR_THUMB  + " TEXT, " +
                    COL_FAVORITO   + " INTEGER DEFAULT 0" +
                    ")";

    private final SQLiteDatabase db;

    public CocktailDAO(Context context) {
        db = DBHelper.getInstance(context).getWritableDatabase();
    }


    public long insertar(Cocktail cocktail) {
        ContentValues values = toContentValues(cocktail);
        return db.insert(TABLE_NAME, null, values);
    }


    public int actualizar(Cocktail cocktail) {
        ContentValues values = toContentValues(cocktail);
        return db.update(TABLE_NAME, values,
                COL_ID_DRINK + " = ?",
                new String[]{cocktail.getIdDrink()});
    }

    public int toggleFavorito(String idDrink, boolean favorito) {
        ContentValues values = new ContentValues();
        values.put(COL_FAVORITO, favorito ? 1 : 0);
        return db.update(TABLE_NAME, values,
                COL_ID_DRINK + " = ?",
                new String[]{idDrink});
    }


    public int eliminar(String id) {
        Log.d("DELETE", "ID = " + id);
        return db.delete(TABLE_NAME,
                COL_ID + " = ?",
                new String[]{id});
    }
    public int eliminarIdDrink(String idDrink) {
        Log.d("DELETE", "ID = " + idDrink);
        return db.delete(TABLE_NAME,
                COL_ID_DRINK + " = ?",
                new String[]{idDrink});
    }

    public int eliminarTodos() {
        return db.delete(TABLE_NAME, null, null);
    }


    public Cocktail obtenerPorIdDrink(String idDrink) {
        Cursor cursor = db.query(TABLE_NAME, null,
                COL_ID_DRINK + " = ?",
                new String[]{idDrink},
                null, null, null);

        Cocktail cocktail = null;
        if (cursor.moveToFirst()) {
            cocktail = cursorToCocktail(cursor);
        }
        cursor.close();
        return cocktail;
    }

    public List<Cocktail> obtenerTodos() {
        List<Cocktail> lista = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,
                null, null, null, null, COL_STR_DRINK + " ASC");

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursorToCocktail(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public List<Cocktail> obtenerFavoritos() {
        List<Cocktail> lista = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,
                COL_FAVORITO + " = 1",
                null, null, null, COL_STR_DRINK + " ASC");

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursorToCocktail(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public boolean esFavorito(String idDrink) {
        Cocktail c = obtenerPorIdDrink(idDrink);
        return c != null && c.isFavorito();
    }

    public List<Cocktail> buscarPorNombre(String nombre) {
        List<Cocktail> lista = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,
                COL_STR_DRINK + " LIKE ?",
                new String[]{"%" + nombre + "%"},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursorToCocktail(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    // HELPERS
    private ContentValues toContentValues(Cocktail c) {
        ContentValues values = new ContentValues();
        values.put(COL_ID_DRINK,  c.getId());
        values.put(COL_STR_DRINK, c.getStrDrink());
        values.put(COL_STR_THUMB, c.getStrDrinkThumb());
        values.put(COL_FAVORITO,  c.isFavorito() ? 1 : 0);
        return values;
    }

    private Cocktail cursorToCocktail(Cursor cursor) {
        Cocktail c = new Cocktail();
        c.setId(cursor.getInt   (cursor.getColumnIndexOrThrow(COL_ID)));
        c.setIdDrink( cursor.getString(cursor.getColumnIndexOrThrow(COL_ID_DRINK)));
        c.setStrDrink(cursor.getString(cursor.getColumnIndexOrThrow(COL_STR_DRINK)));
        c.setStrDrinkThumb(cursor.getString(cursor.getColumnIndexOrThrow(COL_STR_THUMB)));
        c.setFavorito(cursor.getInt   (cursor.getColumnIndexOrThrow(COL_FAVORITO)) == 1);
        return c;
    }
}