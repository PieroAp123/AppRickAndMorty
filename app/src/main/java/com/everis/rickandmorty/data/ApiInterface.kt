package com.everis.rickandmorty.data

import com.everis.rickandmorty.model.characterModels.ListRickAndMorty
import com.everis.rickandmorty.model.episodeModels.EpisodeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("episode")
    fun getRickAndMortyList(@Query("page") page: Int): Call<EpisodeResponse>
}