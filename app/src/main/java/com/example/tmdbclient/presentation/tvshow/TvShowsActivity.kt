package com.example.tmdbclient.presentation.tvshow

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
import com.example.tmdbclient.databinding.ActivityTvShowsBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowsActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: TvShowViewModelFactory

    private lateinit var viewModel: TvShowViewModel
    private lateinit var binding: ActivityTvShowsBinding
    private lateinit var adapter: TvShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_shows)

        (application as Injector).createTvShowSubComponent().inject(this)

        viewModel = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = TvShowAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TvShowsActivity)
            adapter = this@TvShowsActivity.adapter
        }

        downloadPopularTvShows()
    }

    private fun downloadPopularTvShows() {
        viewModel.getTvShows().observe(this) {
            if (!it.isNullOrEmpty()) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No TvShows found", Toast.LENGTH_SHORT).show()
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
                updatePopularTvShowsList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updatePopularTvShowsList() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.updateTvShows().observe(this) {
            if (!it.isNullOrEmpty()) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "No TvShows found", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }
}