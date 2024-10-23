package com.example.testnach

import android.R.attr.textColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(private val pokemons: List<Pokemon>, private val onClick: (Pokemon) -> Unit) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pokemonName: TextView = view.findViewById(R.id.pokemonName)
        val pokemonSprite: ImageView = view.findViewById(R.id.pokemonSprite)
        val initialsTextView: TextView = view.findViewById(R.id.initialsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.pokemonName.text = pokemons[position].name
        val pokemonURL = pokemons[position].url.substringBeforeLast("/").dropLast(0).split('/')
        val pokemonId = pokemonURL.get(pokemonURL.size - 1)

        var placeholderResId: Int? = null
        placeholderResId = R.drawable.placepokebola
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
        var atxtIniciales = Iniciales(pokemons.get(position).name)

            if (imageUrl != "")
            {
                Glide.with(holder.itemView.context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placepokebola) // Placeholder si la imagen no se carga
                    .error(placeholderResId) // Imagen de error si hay un problema
                    .into(holder.pokemonSprite)

                holder.initialsTextView.visibility = View.GONE
            }
        else
            {
                holder.initialsTextView.text = atxtIniciales
                holder.initialsTextView.visibility = View.VISIBLE
                holder.pokemonSprite.visibility = View.GONE
            }

        holder.itemView.setOnClickListener {
            onClick(pokemons[position]) // Manejar el clic para obtener detalles del Pokémon
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    private fun Iniciales(nombre: String): String
    {
        val initials = nombre?.split(" ")
            ?.filter { it.isNotEmpty() && it[0].isLetter() }
            ?.take(2)?.joinToString("") { it[0].uppercase() }

        return initials.toString()

    }

}
