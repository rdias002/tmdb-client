package com.example.tmdbclient.presentation.artist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityArtistsBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class ArtistsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ArtistViewModelFactory

    private lateinit var binding: ActivityArtistsBinding
    private lateinit var viewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artists)

        (application as Injector).createArtistSubComponent().inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(ArtistViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ArtistAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ArtistsActivity)
            adapter = this@ArtistsActivity.adapter
        }

        downloadPopularArtists()
    }

    private fun downloadPopularArtists() {
        viewModel.getArtists().observe(this) {
            if (!it.isNullOrEmpty()) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updatePopularArtist()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun updatePopularArtist() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.updateArtists().observe(this) {
            if (!it.isNullOrEmpty()) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }
}