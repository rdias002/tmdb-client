package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.example.tmdbclient.presentation.di.movie.MovieSubComponent
import com.example.tmdbclient.presentation.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        NetModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        CacheDataModel::class,
        LocalDataModule::class,
        RemoteDataModule::class
    ]
)
interface AppComponent {

    fun movieSubcomponent(): MovieSubComponent.Factory
    fun artistSubcomponent(): ArtistSubComponent.Factory
    fun tvShowSubcomponent(): TvShowSubComponent.Factory
}