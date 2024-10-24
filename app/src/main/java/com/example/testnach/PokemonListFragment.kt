package com.example.testnach


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListFragment() : Fragment() {
    private var offset = 0
    private var limit = 25
    private lateinit var adapter: PokemonAdapter
    private val pokemons = mutableListOf<Pokemon>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        adapter = PokemonAdapter(pokemons) { pokemon -> loadPokemonDetails(pokemon) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadPokemons(offset, limit)

        return view
    }

    private fun loadPokemons(offset: Int, limit: Int) {
        RetrofitInstance.api.getPokemons(offset, limit).enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val newPokemons = response.body()!!.results
                    pokemons.addAll(newPokemons)
                    adapter.notifyDataSetChanged()

                    // Si hay más Pokémon, cargar más
                    if (response.body()!!.next != null) {
                        this@PokemonListFragment.offset += limit // Actualizar el offset
                        loadPokemons(this@PokemonListFragment.offset, limit) // Cargar más Pokémon
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                // Manejar errores de conexión
            }
        })
    }

    private fun loadPokemonDetails(pokemon: Pokemon) {
        val pokemonURL = pokemon.url.substringBeforeLast("/").dropLast(0).split('/')
        val pokemonId = pokemonURL.get(pokemonURL.size - 1)
        RetrofitInstance.api.getPokemonDetail(pokemonId.toInt()).enqueue(object : Callback<PokemonDetail> {
            override fun onResponse(call: Call<PokemonDetail>, response: Response<PokemonDetail>) {
                if (response.isSuccessful && response.body() != null) {
                    val dialog = PokemonDetalle(response.body()!!)
                    // Mostrar el DialogFragment usando el FragmentManager
                    dialog.show(childFragmentManager, "PokemonDetalle")
                }
            }

            override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                // Manejar errores
            }
        })
    }
}