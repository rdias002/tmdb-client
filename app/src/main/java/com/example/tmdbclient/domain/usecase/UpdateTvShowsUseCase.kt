package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.domain.repository.TvShowRepository

class UpdateTvShowsUseCase(private val tvShowRepo: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepo.updateTvShows()
}