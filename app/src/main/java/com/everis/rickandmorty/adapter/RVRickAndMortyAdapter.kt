package com.everis.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.everis.rickandmorty.R
import com.everis.rickandmorty.databinding.ItemCharacterBinding
import com.everis.rickandmorty.model.RickAndMorty
import com.squareup.picasso.Picasso

class RVRickAndMortyAdapter(
    private val rickAndMortyList: List<RickAndMorty>,
    private val onItemClicked: (RickAndMorty) -> Unit
): RecyclerView.Adapter<RVRickAndMortyAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent,false)) {
            onItemClicked(rickAndMortyList[it])
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val rickAndMorty = rickAndMortyList[position]
        holder.bind(rickAndMorty)
    }

    override fun getItemCount() = rickAndMortyList.size

    class CharacterViewHolder(itemView: View, onItemClicked: (Int) -> Unit): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCharacterBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(rickAndMorty: RickAndMorty) {
            Picasso.get().load(rickAndMorty.image).into(binding.imgRickAndMorty)
            binding.txtCharacterNameRickAndMorty.text = rickAndMorty.name
        }
    }

}