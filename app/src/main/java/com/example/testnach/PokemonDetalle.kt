package com.example.testnach

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide

class PokemonDetalle(val aDetalle: PokemonDetail) : DialogFragment(R.layout.fragment_pokemon_detalle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_detalle, container, false)
        val pokemonSprite = view.findViewById<ImageView>(R.id.pokemonSprite)
        val pokemonDetalleId = view.findViewById<TextView>(R.id.pokemonDetalleId)
        val pokemonDetalleNombre = view.findViewById<TextView>(R.id.pokemonDetalleNombre)
        val pokemonDetalleAlto = view.findViewById<TextView>(R.id.pokemonDetalleAlto)
        val pokemonDetalleAncho = view.findViewById<TextView>(R.id.pokemonDetalleAncho)
        Glide.with(this)
            .load(aDetalle.sprites.front_default)
            .placeholder(R.drawable.placepokebola)
            .error(R.drawable.placepokebola)
            .into(pokemonSprite)

        pokemonDetalleNombre.text = aDetalle.name
        pokemonDetalleId.text= aDetalle.id.toString()
        pokemonDetalleAlto.text = aDetalle.height.toString()
        pokemonDetalleAncho.text = aDetalle.weight.toString()

        return view
    }

    override fun onStart() {
        super.onStart()
        // Ajustar el ancho del DialogFragment a MATCH_PARENT
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}