package com.everis.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.everis.rickandmorty.R
import com.everis.rickandmorty.model.RickAndMorty
import com.squareup.picasso.Picasso

class RVRickAndMortyAdapter(
    private val rickAndMortyList: List<RickAndMorty>
): RecyclerView.Adapter<RVRickAndMortyAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent,false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val rickAndMorty = rickAndMortyList[position]
        holder.bind(rickAndMorty, position)
    }

    override fun getItemCount() = rickAndMortyList.size

    class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(rickAndMorty: RickAndMorty, position: Int) {
            val txtRickAndMortyName = itemView.findViewById<TextView>(R.id.txt_characterName_rickAndMorty)
            val imgRickAndMorty = itemView.findViewById<ImageView>(R.id.img_rickAndMorty)
            Picasso.get().load(rickAndMorty.image).into(imgRickAndMorty)
            txtRickAndMortyName.text = rickAndMorty.name
        }
    }

}