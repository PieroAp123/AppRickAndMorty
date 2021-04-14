package com.everis.rickandmorty.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.everis.rickandmorty.adapter.RVRickAndMortyAdapter
import com.everis.rickandmorty.data.ApiService
import com.everis.rickandmorty.data.ClientConfig
import com.everis.rickandmorty.model.RickAndMorty
import com.everis.rickandmorty.databinding.ActivityRickandmortyBinding
import com.everis.rickandmorty.model.ListRickAndMorty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RickAndMortyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRickandmortyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickandmortyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRickAndMorty()

    }

     private fun getRickAndMorty() {
         val service = ApiService(config = ClientConfig())
         CoroutineScope(Dispatchers.IO).launch {
             service.getRickAndMortyList().enqueue(object : Callback<ListRickAndMorty> {
                 override fun onResponse(call: Call<ListRickAndMorty>, response: Response<ListRickAndMorty>) {
                     if (response.isSuccessful) {
                         showRickAndMorty(response.body()!!.results)
                         Log.e("successful","Llamada del servicio correctamente")
                     }

                 }

                 override fun onFailure(call: Call<ListRickAndMorty>, t: Throwable) {
                     Log.e("unsuccessful","Llamada del servicio error")
                 }

             })
         }
    }

    fun showRickAndMorty(rickAndMorty: List<RickAndMorty>) {
        rickAndMorty.let {
            val adapter = RVRickAndMortyAdapter(it)
            binding.rvRickAndMortyList.adapter = adapter
            binding.rvRickAndMortyList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }
}

