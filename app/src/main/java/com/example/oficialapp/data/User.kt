package com.example.oficialapp.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    // Otros atributos del usuario..
)
