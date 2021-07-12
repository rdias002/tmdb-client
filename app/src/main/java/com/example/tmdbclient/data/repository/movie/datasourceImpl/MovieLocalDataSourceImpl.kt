package com.example.tmdbclient.data.repository.movie.datasourceImpl

import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class MovieLocalDataSourceImpl(private val movieDao: MovieDao) :
    MovieLocalDataSource {

    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        coroutineScope {
            launch(Dispatchers.IO) {
                movieDao.saveMovies(movies)
            }
        }
    }

    override suspend fun clearAll() {
        coroutineScope {
            launch(Dispatchers.IO) {
                movieDao.deleteAllMovies()
            }
        }
    }
}