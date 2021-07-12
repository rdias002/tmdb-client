package com.example.tmdbclient.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityHomeBinding
import com.example.tmdbclient.presentation.artist.ArtistsActivity
import com.example.tmdbclient.presentation.movie.MoviesActivity
import com.example.tmdbclient.presentation.tvshow.TvShowsActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.movieButton.setOnClickListener {
            startActivity(Intent(this, MoviesActivity::class.java))
        }

        binding.tvButton.setOnClickListener {
            startActivity(Intent(this, TvShowsActivity::class.java))
        }

        binding.artistButton.setOnClickListener {
            startActivity(Intent(this, ArtistsActivity::class.java))
        }

    }
}