package com.example.oficialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.oficialapp.data.ApiService
import com.example.oficialapp.data.RetrofitClient
import com.example.oficialapp.data.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.editTextEmaill)
        editTextPassword = findViewById(R.id.editTextPasswordd)

        val textViewRegister = findViewById<TextView>(R.id.textViewRegister)
        textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Por favor, ingresa tu correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }



    }

    private fun loginUser(email: String, password: String) {
        val loginData = HashMap<String, String>()
        loginData["email"] = email
        loginData["password"] = password

        val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
        val call = apiService.loginUser(loginData)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    // Inicio de sesión exitoso, redirige a la pantalla principal o HomeActivity
                    val intent = Intent(this@MainActivity, CasaActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // No se pudo iniciar sesión, muestra un mensaje de error
                    Toast.makeText(this@MainActivity, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Error al intentar iniciar sesión, muestra un mensaje de error
                Toast.makeText(this@MainActivity, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
            }
        }
        )

    }
}

