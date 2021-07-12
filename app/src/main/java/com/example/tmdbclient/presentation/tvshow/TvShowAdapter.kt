package com.example.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.databinding.ListItemBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private val tvShowList = ArrayList<TvShow>()

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
        val tvShow = tvShowList[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    fun setList(newTvShowList: List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(newTvShowList)
    }

    class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.apply {
                titleTextView.text = tvShow.name
                descriptionTextView.text = tvShow.overview
                val posterUrl = "https://image.tmdb.org/t/p/w500" + tvShow.poster_path
                Glide.with(imageView.context)
                    .load(posterUrl)
                    .into(imageView)
            }
        }
    }
}