package com.everis.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.everis.rickandmorty.R
import com.everis.rickandmorty.databinding.ItemCharacterBinding
import com.everis.rickandmorty.model.characterModels.RickAndMorty
import com.everis.rickandmorty.model.episodeModels.EpisodeDetail
import com.squareup.picasso.Picasso

class CharacterAdapter(
        private val characterList: List<RickAndMorty>,
        private val onItemClicked: (RickAndMorty) -> Unit
): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent,false)) {
            onItemClicked(characterList[it])
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
    }

    override fun getItemCount() = characterList.size

    class CharacterViewHolder(itemView: View, onItemClicked: (Int) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCharacterBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(character: RickAndMorty) {
            Picasso.get().load(character.image).into(binding.imgRickAndMorty)
            binding.txtCharacterNameRickAndMorty.text = character.name
        }
    }
}