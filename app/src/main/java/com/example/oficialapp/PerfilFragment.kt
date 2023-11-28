package com.example.oficialapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class PerfilFragment : Fragment() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonUpdate: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        editTextName = view.findViewById(R.id.editTextNamee)
        editTextEmail = view.findViewById(R.id.editTextEmaill)
        editTextPassword = view.findViewById(R.id.editTextPasswordd)
        buttonUpdate = view.findViewById(R.id.buttonUpdate)

        // Manejar clic del botón de actualización
        buttonUpdate.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Crear el objeto JSON con los datos actualizados del usuario
                val updatedUser = JSONObject()
                updatedUser.put("name", name)
                updatedUser.put("email", email)
                updatedUser.put("password", password)

                // Realizar la solicitud PUT a la API para actualizar los datos del usuario
                updateUserData(updatedUser)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Por favor, completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }

    private fun updateUserData(updatedUserData: JSONObject) {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://domofticaweb-production.up.railway.app/api/update"

        val sharedPref = requireActivity().getSharedPreferences("com.example.oficialapp.PREFERENCES", Context.MODE_PRIVATE)
        val token = sharedPref.getString("TOKEN_KEY", "") ?: ""

        val request = object : JsonObjectRequest(Method.PUT, url, updatedUserData,
            { response ->
                // Manejar la respuesta de la API si es necesario
                Toast.makeText(
                    requireContext(),
                    "Datos actualizados correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            },
            { error ->
                // Manejar errores de la solicitud
                Toast.makeText(
                    requireContext(),
                    "Error al actualizar datos: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $token" // Incluir el token de acceso en los encabezados de autenticación
                return headers
            }
        }

        queue.add(request)
    }

}