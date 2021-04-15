package com.everis.rickandmorty.model.episodeModels

import com.everis.rickandmorty.model.characterModels.CharacterEndpointInfo
import com.everis.rickandmorty.model.characterModels.RickAndMorty
import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("info")
    val info: EpisodeEndpointInfo,
    @SerializedName("results")
    val results: List<EpisodeDetail>
)
