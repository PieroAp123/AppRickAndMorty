package com.everis.rickandmorty.model.characterModels

import com.google.gson.annotations.SerializedName

data class ListRickAndMorty(
    @SerializedName("info")
    val info: CharacterEndpointInfo,
    @SerializedName("results")
    val results: List<RickAndMorty>
)