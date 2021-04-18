package com.everis.rickandmorty.controller

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.everis.rickandmorty.databinding.ActivityEpisodeDetailBinding
import com.everis.rickandmorty.databinding.ActivityRickandmortyBinding
import com.everis.rickandmorty.databinding.SplashActivityBinding

class SplashActivity: AppCompatActivity() {
    private lateinit var binding: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this,RickAndMortyActivity::class.java))
            finish()
        }, 3000)




    }

    override fun onStart() {
        super.onStart()
        supportActionBar?.hide()
    }
}