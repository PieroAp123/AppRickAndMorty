package com.everis.rickandmorty.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class ApiService(
    private val config: ClientConfig
) : ApiInterface by Retrofit.Builder()
    .baseUrl(config.rootUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient.Builder().(config.okHttpClient)().build())
    .build()
    .create(ApiInterface::class.java){
}