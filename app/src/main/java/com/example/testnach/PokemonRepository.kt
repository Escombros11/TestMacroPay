package com.example.testnach

import androidx.lifecycle.LiveData
import com.example.testnach.Interfaces.PokemonDao

class PokemonRepository(private val pokemonDao: PokemonDao) {
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        pokemonDao.updateFavoriteStatus(id, isFavorite)
    }

    fun getFavoritePokemon(): LiveData<List<PokemonEntity>> {
        return pokemonDao.getFavoritePokemon()
    }
}
