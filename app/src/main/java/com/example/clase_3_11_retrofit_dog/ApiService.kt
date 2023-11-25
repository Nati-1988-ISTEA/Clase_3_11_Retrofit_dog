package com.example.clase_3_11_retrofit_dog

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getImagesByBreed(@Url url: String): Response<BreedResponse>
    //la funcion tiene que estar en suspenso

    @GET
    suspend fun getListOfBreeds(@Url url: String): Response<BreedResponse>
    //la funcion tiene que estar en suspenso

}