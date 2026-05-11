package com.example.repasomayo.modelo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("filter.php?a=Alcoholic")
    Call<CocktailResponse> getCocktails();

}
