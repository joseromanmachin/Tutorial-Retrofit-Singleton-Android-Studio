package com.example.tutorialretrofit.dataAcces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("api.php")
    Call<List<Estado>> getEstados();
}
