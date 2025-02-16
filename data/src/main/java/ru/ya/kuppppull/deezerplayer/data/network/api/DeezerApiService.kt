package ru.ya.kuppppull.deezerplayer.data.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.ya.kuppppull.deezerplayer.data.network.model.ChartResponse
import ru.ya.kuppppull.deezerplayer.data.network.model.SearchResponse
import ru.ya.kuppppull.deezerplayer.data.network.model.SongNetwork

interface DeezerApiService {
    @GET("chart")
    suspend fun getChart(): ChartResponse

    @GET("search")
    suspend fun searchSongs(@Query("q") query: String): SearchResponse

    @GET("track/{id}")
    suspend fun getSong(@Path("id") query: String): SongNetwork
}

