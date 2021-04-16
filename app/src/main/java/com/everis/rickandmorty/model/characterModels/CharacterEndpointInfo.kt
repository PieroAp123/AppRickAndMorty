package com.everis.rickandmorty.model.characterModels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterEndpointInfo(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("pages")
    val pages: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
) : Serializable
