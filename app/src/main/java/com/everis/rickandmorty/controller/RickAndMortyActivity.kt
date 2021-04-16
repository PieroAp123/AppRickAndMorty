package com.everis.rickandmorty.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.everis.rickandmorty.adapter.RVRickAndMortyAdapter
import com.everis.rickandmorty.data.ApiService
import com.everis.rickandmorty.data.ClientConfig
import com.everis.rickandmorty.databinding.ActivityRickandmortyBinding
import com.everis.rickandmorty.model.episodeModels.EpisodeDetail
import com.everis.rickandmorty.model.episodeModels.EpisodeResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RickAndMortyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRickandmortyBinding
    private val episodesList = mutableListOf<EpisodeDetail>()
    private lateinit var adapter: RVRickAndMortyAdapter
    private var canLoad = false
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickandmortyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        canLoad = true
        getRickAndMorty()

    }

     private fun getRickAndMorty() {
         val service = ApiService(config = ClientConfig())
         CoroutineScope(Dispatchers.IO).launch {
             service.getRickAndMortyEpisodeList(page).enqueue(object : Callback<EpisodeResponse> {
                 override fun onResponse(call: Call<EpisodeResponse>, response: Response<EpisodeResponse>) {
                     if (response.isSuccessful) {
                         canLoad = true
                         episodesList.addAll(response?.body()?.results ?: emptyList())
                         adapter.notifyDataSetChanged()
                         Log.e("successful","Llamada del servicio correctamente")
                     }
                 }

                 override fun onFailure(call: Call<EpisodeResponse>, t: Throwable) {
                     canLoad = true
                     Log.e("unsuccessful","Llamada del servicio error")
                 }

             })
         }
    }

    fun initRecyclerView() {
            adapter = RVRickAndMortyAdapter(episodesList) {
                val intent = Intent(this, EpisodeDetailActivity::class.java).apply {
                    Log.e("q", it.episode!!)
                    Log.e("q", it.name!!)
                    Log.e("q", it.air_date!!)
                    putExtra("episodeDetail", it)
                }
                startActivity(intent)
            }
            binding.rvRickAndMortyList.adapter = adapter
            binding.rvRickAndMortyList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rvRickAndMortyList.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if(dy > 0){
                        var visibleItemCount = (binding.rvRickAndMortyList.layoutManager as LinearLayoutManager).childCount
                        var totalItemCount = (binding.rvRickAndMortyList.layoutManager as LinearLayoutManager).itemCount
                        var pastVisibleItems = (binding.rvRickAndMortyList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                        if(canLoad) {
                            if((visibleItemCount + pastVisibleItems) >= totalItemCount ) {
                                Log.i("RickAndMorty", "Reached the end")
                                canLoad = false
                                page += 1
                                getRickAndMorty()
                            }
                        }
                    }
                    Log.e("q", dy.toString())
                }
            })
        }

}

