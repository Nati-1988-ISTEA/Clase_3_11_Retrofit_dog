package com.example.clase_3_11_retrofit_dog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var listadoDeImaganes = mutableListOf<String>() // creo una lista de imagenes
   // private lateinit var spinner : Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //spinner = findViewById(R.id.spinner)

        adapter = Adapter(listadoDeImaganes)
        recyclerView.adapter = adapter

        getListOfImagesByBreed()


    }

    private fun getListOfImagesByBreed() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getImagesByBreed("breed/hound/images")
            val response = call.body() // lo lleno con la respuesta obtenida

            runOnUiThread {
                if (call.isSuccessful) {
                    val images = response?.breeds ?: listOf() //  val images = response?.breeds ?: emptyList()
                    listadoDeImaganes.clear()
                    listadoDeImaganes.addAll(images)
                    adapter.notifyDataSetChanged() // los datos han cambviado y lo vuelve a enviar
                    // el adapter esta dentro del hilo prinipal y no puede
                    Toast.makeText(this@MainActivity, "Llamada exitosa", Toast.LENGTH_SHORT).show()
                }
            }
        }
        // Dispatchers.IO corrutina entrada y salida
        // Dispatchers.Main cuando es una corrutina muy compleja
        // Dispatchers.Unconfined para test
    }


    private fun getRetrofit(): Retrofit {
        // devolver una istancia de retrofit
        // necesita 2 cosa base url (la parte del hiperv que no cambia) y un converter factory

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }
}