package ru.ya.kuppppull.deezerplayer.app

import android.app.Application
import ru.ya.kuppppull.deezerplayer.di.DaggerAppComponent
import ru.ya.kuppppull.deezerplayer.di.DiProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        val appComponent = DaggerAppComponent
            .builder()
            .addContext(this)
            .build()

        DiProvider.di = appComponent
    }
}