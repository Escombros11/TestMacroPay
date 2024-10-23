package com.example.testnach

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Inicio : AppCompatActivity() {
    var imageUrl: String? = ""
    var name: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

            val pokemonListFragment = PokemonListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, pokemonListFragment)
                .commit()
    }
}