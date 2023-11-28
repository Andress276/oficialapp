package com.example.oficialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.oficialapp.databinding.ActivityCasaBinding
import com.google.android.material.navigation.NavigationView

class CasaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityCasaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCasaBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.app_open, R.string.app_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> replaceFragment(HomeFragment())
                R.id.bottom_Funcionalidad -> replaceFragment(FuncinlidadFragment())
                R.id.bottom_contact -> replaceFragment(ContacFragment())
                R.id.bottom_somos -> replaceFragment(SomosFragment())
            }
            true // Indicar que se ha manejado la selección del elemento
        }

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> openFragment(HomeFragment())
            R.id.nav_perfil -> openFragment(PerfilFragment())
            R.id.nav_configurar -> openFragment(ConfigFragment())
            R.id.nav_calificar -> openFragment(CalifFragment())
            R.id.nav_compartir -> openFragment(CompartirFragment())
            R.id.nav_riego -> openFragment(RiegoFragment())

            R.id.nav_cerrar -> {
                // Lógica para cerrar sesión (borrar tokens, limpiar datos de sesión, etc.)
                // Por ejemplo, puedes utilizar SharedPreferences para limpiar datos de sesión.
                val sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                // Redirigir al usuario a la pantalla de inicio de sesión (MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Cierra la actividad actual para evitar volver atrás
            }

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }

    private fun openFragment(fragment:Fragment){
        val fragmentTransaction:FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}