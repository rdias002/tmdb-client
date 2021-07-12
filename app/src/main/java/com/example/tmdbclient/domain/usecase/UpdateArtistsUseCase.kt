package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.domain.repository.ArtistRepository

class UpdateArtistsUseCase(private val artistRepo: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepo.updateArtists()
}