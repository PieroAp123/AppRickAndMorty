package com.everis.rickandmorty.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.everis.rickandmorty.adapter.CharacterAdapter
import com.everis.rickandmorty.controller.CharacterDetail.CharacterDetailActivity
import com.everis.rickandmorty.data.ApiService
import com.everis.rickandmorty.data.ClientConfig
import com.everis.rickandmorty.databinding.ActivityEpisodeDetailBinding
import com.everis.rickandmorty.model.characterModels.RickAndMorty
import com.everis.rickandmorty.model.episodeModels.EpisodeDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEpisodeDetailBinding
    private val episodeCharactersList = mutableListOf<RickAndMorty>()
    private lateinit var adapter: CharacterAdapter
    private var characterListString = ""
    private var episodeDetail: EpisodeDetail? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        episodeDetail = this.intent.getSerializableExtra("episodeDetail") as EpisodeDetail

        binding.episodeID.text = episodeDetail?.episode
        binding.episodeName.text = episodeDetail?.name
        binding.airDate.text = episodeDetail?.air_date
        initRecyclerView()
        getCharacterListString()
        getEpisodeCharactersList()
        binding.arrowLeftEpisodeDetail.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getCharacterListString() {
        for (i in episodeDetail?.characters!!.indices) {
            val parts = episodeDetail!!.characters[i].split("/")
            if (i < episodeDetail!!.characters.size - 1) {
                characterListString += parts.last() + ","
            } else {
                characterListString += parts.last()
            }
        }
    }

    private fun getEpisodeCharactersList() {
        val service = ApiService(config = ClientConfig())
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("characterListString",characterListString)
            service.getEpisodeCharactersList(characterListString).enqueue(object : Callback<List<RickAndMorty>> {
                override fun onResponse(call: Call<List<RickAndMorty>>, response: Response<List<RickAndMorty>>) {
                    if (response.isSuccessful) {
                        episodeCharactersList.addAll(response.body() ?: emptyList())
                        adapter.notifyDataSetChanged()
                        Log.e("successful","Llamada del servicio correctamente")
                    }
                }

                override fun onFailure(call: Call<List<RickAndMorty>>, t: Throwable) {
                    //canLoad = true
                    Log.e("unsuccessful","Llamada del servicio error")
                }

            })
        }
    }

    fun initRecyclerView() {
        adapter = CharacterAdapter(episodeCharactersList) {
            val intent = Intent(this, CharacterDetailActivity::class.java).apply {
                putExtra("characterDetail", it)
            }
            startActivity(intent)
        }
        binding.charactersRecyclerView.adapter = adapter
        binding.charactersRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        /*binding.charactersRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
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
        })*/
    }
}