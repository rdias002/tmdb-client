package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.presentation.tvshow.TvShowsActivity
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {

    fun inject(tvShowsActivity: TvShowsActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TvShowSubComponent
    }
}