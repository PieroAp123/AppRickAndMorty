package com.everis.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class ListRickAndMorty(
    @SerializedName("results")
    val results: List<RickAndMorty>
)