package com.example.oficialapp.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    @POST("login") //endpoint inicio de sesión
    fun loginUser(@Body loginData: HashMap<String, String>): Call<User>

    @GET("users") // Reemplaza "endpoint/usuarios" con tu endpoint real
    suspend fun obtenerUsuarios(): List<Usuario>

}
