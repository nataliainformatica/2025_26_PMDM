package com.example.mapsretrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Url;

public interface Servicio {

    @GET
    Call<List<Ciudad>> getUserPosts(@Url String url);
}
