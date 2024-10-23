package com.example.testnach

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PokemonViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PokemonRepository

    init {
        val pokemonDao = AppDatabase.getDatabase(application).pokemonDao()
        repository = PokemonRepository(pokemonDao)
    }

    fun toggleFavorite(pokemon: PokemonEntity) {
        viewModelScope.launch {
            val newFavoriteStatus = !pokemon.isFavorite
            repository.updateFavoriteStatus(pokemon.id, newFavoriteStatus)
        }
    }

    fun getFavoritePokemon(): LiveData<List<PokemonEntity>> {
        return repository.getFavoritePokemon()
    }
}
