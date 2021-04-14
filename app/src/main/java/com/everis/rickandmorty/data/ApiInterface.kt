package com.everis.rickandmorty.data

import com.everis.rickandmorty.model.ListRickAndMorty
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("character")
    fun getRickAndMortyList(): Call<ListRickAndMorty>
}