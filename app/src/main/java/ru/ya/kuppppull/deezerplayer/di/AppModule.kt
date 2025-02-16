package ru.ya.kuppppull.deezerplayer.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.ya.kuppppull.deezerapi.data.FilesScanner
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
        fileManager: FilesScanner
    ) : TrackRepository{
        return TrackRepositoryImpl(
            api = api,
            fileManager = fileManager
        )
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
}