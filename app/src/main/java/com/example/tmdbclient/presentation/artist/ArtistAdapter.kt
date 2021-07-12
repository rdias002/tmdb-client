package com.example.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.databinding.ListItemBinding

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    private val artistsList = ArrayList<Artist>()

    class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(artist: Artist) {
            binding.apply {
                titleTextView.text = artist.name
                descriptionTextView.text = artist.popularity.toString()
                val posterUrl = "https://image.tmdb.org/t/p/w500" + artist.profile_path
                Glide.with(imageView.context)
                    .load(posterUrl)
                    .into(imageView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artistsList[position])
    }

    override fun getItemCount(): Int {
        return artistsList.size
    }

    fun setList(newList: List<Artist>) {
        artistsList.clear()
        artistsList.addAll(newList)
    }
}