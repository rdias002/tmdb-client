package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.db.TvShowDao
import com.example.tmdbclient.data.model.tvshow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao) :
    TvShowLocalDataSource {

    override suspend fun getTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getTvShows()
    }

    override suspend fun saveTvShowsToDB(tvShows: List<TvShow>) {
        coroutineScope {
            launch(Dispatchers.IO) {
                tvShowDao.saveTvShows(tvShows)
            }
        }
    }

    override suspend fun clearAll() {
        coroutineScope {
            launch(Dispatchers.IO) {
                tvShowDao.deleteAllTvShows()
            }
        }
    }
}