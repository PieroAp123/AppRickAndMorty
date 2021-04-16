package com.everis.rickandmorty.controller.CharacterDetail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.everis.rickandmorty.databinding.ItemCharacterDetailActivityBinding
import com.everis.rickandmorty.model.characterModels.RickAndMorty
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var binding: ItemCharacterDetailActivityBinding
    private var characterDetail: RickAndMorty? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemCharacterDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterDetail = this.intent.getSerializableExtra("characterDetail") as RickAndMorty


        binding.txtNameDetail.text = characterDetail?.name
        binding.txtEspecieDetail.text = characterDetail?.species
        binding.txtStatus.text = characterDetail?.status
        binding.txtGenero.text = characterDetail?.gender
        binding.txtOrigen.text = characterDetail?.origin?.name
        binding.txtLocalizacion.text = characterDetail?.location?.name
        Picasso.get().load(characterDetail?.image).into(binding.imgCharacterDetail)

        binding.arrowLeftCharacterDetail.setOnClickListener { onBackPressed() }

    }
}