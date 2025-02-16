package ru.ya.kuppppull.deezerplayer.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.ya.kuppppull.deezerplayer.psesentation.tracks_from_api.TracksFromApiViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {


    fun TracksFromApiViewModelFactory(): TracksFromApiViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun addContext(context: Context): Builder
        fun build(): AppComponent
    }
}