package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.presentation.movie.MoviesActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(artistActivity: MoviesActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }
}