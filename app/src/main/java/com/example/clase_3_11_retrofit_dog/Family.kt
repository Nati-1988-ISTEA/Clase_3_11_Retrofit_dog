package com.example.clase_3_11_retrofit_dog
// los json siempre en camelcase
data class Family(
    val id:  Int,
    val familyName : String,
    val data: FamilyData,
    val miembros: List <Miembro>,

)
data class FamilyData(
    val ciudadOrigen : String,
    val hichaDe : String,
    val comidaFavorita : String

    )

data class  Miembro(
    val rol: Rol,
    val nombre: String,
    val edad : Int,
    val isAlive : Boolean
)

enum class Rol {
    PADRE, MADRE, HIJA, HIJO
}