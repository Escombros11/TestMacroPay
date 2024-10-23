package com.example.testnach.Interfaces

import com.example.testnach.PokemonDetail
import com.example.testnach.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/{id}")
    fun getPokemonDetail(
        @Path("id") id: Int): Call<PokemonDetail>

    @GET("pokemon/")
    fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<PokemonResponse>
}