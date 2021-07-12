package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRepository
import com.example.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideUpdateMoviesUseCase(movieRepo: MovieRepository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(movieRepo)
    }

    @Provides
    fun provideGetMoviesUseCase(movieRepo: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepo)
    }


    @Provides
    fun provideUpdateTvShowsUseCase(TvShowrepo: TvShowRepository): UpdateTvShowsUseCase {
        return UpdateTvShowsUseCase(TvShowrepo)
    }

    @Provides
    fun provideGetTvShowsUseCase(TvShowepo: TvShowRepository): GetTvShowsUseCase {
        return GetTvShowsUseCase(TvShowepo)
    }


    @Provides
    fun provideUpdateArtistsUseCase(ArtistRepo: ArtistRepository): UpdateArtistsUseCase {
        return UpdateArtistsUseCase(ArtistRepo)
    }

    @Provides
    fun provideGetArtistsUseCase(ArtistRepo: ArtistRepository): GetArtistsUseCase {
        return GetArtistsUseCase(ArtistRepo)
    }
}