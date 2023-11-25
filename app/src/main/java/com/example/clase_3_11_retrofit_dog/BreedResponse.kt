package com.example.clase_3_11_retrofit_dog

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("message")val breeds: List<String>, // para que entendamos que es una imagen aunque tiene que coincidir con la clave del json
    val status: String
)
//el nombre tiene que coincidir con el el del json

class BreedResponses(

)
