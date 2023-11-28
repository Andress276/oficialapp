package com.example.oficialapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oficialapp.data.ApiService
import com.example.oficialapp.data.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BienvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienv) // Reemplaza con tu layout

        val retrofit = Retrofit.Builder()
            .baseUrl("https://domofticaweb-production.up.railway.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val listaUsuarios = apiService.obtenerUsuarios()
                // Procesar la lista de usuarios obtenida
                withContext(Dispatchers.Main) {
                    mostrarUsuarios(listaUsuarios)
                }
            } catch (e: Exception) {
                // Manejar errores
            }
        }
    }

    private fun mostrarUsuarios(usuarios: List<Usuario>) {
        // Actualizar la UI con la lista de usuarios y sus roles
        // Por ejemplo, usar un RecyclerView o ListView para mostrarlos

        mostrarUsuarios(usuarios)
    }
}