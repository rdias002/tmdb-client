package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {

    override suspend fun getArtists(): List<Artist>? {
        return getArtistFromCacheDataSource()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newList = getArtistFromRemoteDataSource()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newList)
        artistCacheDataSource.saveArtistsToCache(newList)
        return newList
    }

    private suspend fun getArtistFromRemoteDataSource(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtistsFromAPI()
            val body = response.body()
            if (body != null) {
                artistList = body.artists
            }
        } catch (exception: Exception) {
            Log.i("MYTAG", "getArtistFromRemoteDataSource: ${exception.message}")
        }

        return artistList
    }

    private suspend fun getArtistFromLocalDataSource(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistLocalDataSource.getArtistsFromDB()
        } catch (exception: Exception) {
            Log.i("MYTAG", "getArtistFromLocalDataSource: ${exception.message}")
        }

        if (artistList.isEmpty()) {
            artistList = getArtistFromRemoteDataSource()
            artistLocalDataSource.saveArtistsToDB(artistList)
        }

        return artistList
    }

    private suspend fun getArtistFromCacheDataSource(): List<Artist> {
        lateinit var artistList: List<Artist>
        try {
            artistList = artistCacheDataSource.getArtistsFromCache()
        } catch (exception: Exception) {
            Log.i("MYTAG", "getArtistFromCacheDataSource: ${exception.message}")
        }

        if (artistList.isEmpty()) {
            artistList = getArtistFromLocalDataSource()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }

        return artistList
    }


}