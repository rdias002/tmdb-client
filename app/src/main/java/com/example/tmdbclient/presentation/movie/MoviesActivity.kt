package com.example.tmdbclient.presentation.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityMoviesBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var movieViewModelFactory: MovieViewModelFactory
    lateinit var adapter: MovieAdapter

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMoviesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)

        (application as Injector).createMovieSubComponent()
            .inject(this)

        movieViewModel = ViewModelProvider(this, movieViewModelFactory)
            .get(MovieViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = MovieAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MoviesActivity)
            adapter = this@MoviesActivity.adapter
        }
        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateMoviesList()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateMoviesList() {
        binding.progressBar.visibility = VISIBLE
        movieViewModel.updateMovies().observe(this) {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = GONE
        }
    }
}