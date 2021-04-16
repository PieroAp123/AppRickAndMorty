package com.everis.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.everis.rickandmorty.R
import com.everis.rickandmorty.databinding.ItemCharacterBinding
import com.everis.rickandmorty.model.episodeModels.EpisodeDetail
import com.squareup.picasso.Picasso

class RVRickAndMortyAdapter(
    private val episodeList: List<EpisodeDetail>,
    private val onItemClicked: (EpisodeDetail) -> Unit
): RecyclerView.Adapter<RVRickAndMortyAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EpisodeViewHolder(layoutInflater.inflate(R.layout.item_character, parent,false)) {
            onItemClicked(episodeList[it])
        }
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episodeList = episodeList[position]
        holder.bind(episodeList)
    }

    override fun getItemCount() = episodeList.size

    class EpisodeViewHolder(itemView: View, onItemClicked: (Int) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCharacterBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(episode: EpisodeDetail) {
            //Picasso.get().load(episode.url).into(binding.imgRickAndMorty)
            Picasso.get().load(R.drawable.rickstrange).into(binding.imgRickAndMorty)
            binding.txtCharacterNameRickAndMorty.text = episode.name
        }
    }

}