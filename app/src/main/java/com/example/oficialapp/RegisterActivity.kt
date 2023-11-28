package com.example.oficialapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.oficialapp.data.SharedPreferencesManager
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        editTextName = findViewById(R.id.editTextNamee)
        editTextEmail = findViewById(R.id.editTextEmaill)
        editTextPassword = findViewById(R.id.editTextPasswordd)

        val activityContext = this

        val buttonRegister = findViewById<Button>(R.id.buttonregister)
        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            // Verificar que los campos no estén vacíos
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                // Crear el objeto JSON con los datos del usuario
                val user = JSONObject()
                user.put("name", name)
                user.put("email", email)
                user.put("password", password)

                // Realizar la solicitud POST a la API
                sendDataToAPI(user, activityContext)
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        val textViewRegister = findViewById<TextView>(R.id.textViewRegister)
        textViewRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad
        }
    }

    private fun sendDataToAPI(userData: JSONObject, activityContext: RegisterActivity) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://domofticaweb-production.up.railway.app/api/"

        val request = JsonObjectRequest(Request.Method.POST, url, userData,
            { response ->
                // Manejar la respuesta de la API si es necesario
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                val userName = response.getString("name") // Asegúrate de que el nombre de usuario coincida con el campo devuelto por la API
                SharedPreferencesManager.guardarNombreUsuario(this, userName)


            },
            { error ->
                // Manejar errores de la solicitud
                Toast.makeText(this, "Error al registrar: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        queue.add(request)
    }
}
