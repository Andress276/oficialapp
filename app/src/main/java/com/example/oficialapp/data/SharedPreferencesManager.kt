package com.example.oficialapp.data

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {
    private const val PREF_NAME = "NombreDeTuApp"
    private const val KEY_NOMBRE_USUARIO = "nombreUsuario"

    fun guardarNombreUsuario(context: Context, nombreUsuario: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_NOMBRE_USUARIO, nombreUsuario)
        editor.apply()
    }

    fun obtenerNombreUsuario(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_NOMBRE_USUARIO, null)
    }
}