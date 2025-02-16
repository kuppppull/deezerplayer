package ru.ya.kuppppull.deezerplayer.di

import android.content.ComponentName
import android.content.Context
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.ya.kuppppull.deezerplayer.data.FilesScanner
import ru.ya.kuppppull.deezerplayer.service.PlaybackService
import ru.ya.kuppppull.deezerplayer.data.Mp3Downloader
import ru.ya.kuppppull.deezerplayer.data.network.api.DeezerApiService
import ru.ya.kuppppull.deezerplayer.data.repository.TrackRepositoryImpl
import ru.ya.kuppppull.deezerplayer.domain.repository.TrackRepository
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideTrackRepository(
        api: DeezerApiService,
        fileManager: FilesScanner,
        downloader: Mp3Downloader
    ): TrackRepository {
        return TrackRepositoryImpl(
            api = api,
            fileManager = fileManager,
            downloader = downloader
        )
    }

    @Singleton
    @Provides
    fun provideMp3Downloader(context: Context): Mp3Downloader {
        return Mp3Downloader(context)
    }

    @Singleton
    @Provides
    fun provideScanner(context: Context): FilesScanner {
        return FilesScanner(context)
    }


    @Provides
    fun provideDeezerApi(): DeezerApiService {

        val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(DeezerApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMediaController(context: Context) : ListenableFuture<MediaController> {
        return MediaController
            .Builder(
                context,
                SessionToken(
                    context,
                    ComponentName(context, PlaybackService::class.java)
                )
            )
            .buildAsync()
    }
}