package com.example.petshare;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiPet {

    @Headers({"Accept: application/json"})
    @GET("user/viewallpets")
    Call<List<PetResponse>> getAllPets();
}
