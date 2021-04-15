package com.everis.rickandmorty.model.episodeModels
import com.google.gson.annotations.SerializedName

data class EpisodeDetail(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("air_date")
    val air_date: String?,
    @SerializedName("episode")
    val episode: String?,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("url")
    val url: String?,
    @SerializedName("created")
    val created: String?,
)