package ru.ya.kuppppull.deezerplayer.data.repository

import ru.ya.kuppppull.deezerplayer.data.mappers.toDomain
import ru.ya.kuppppull.deezerplayer.data.network.api.DeezerApiService
import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.domain.repository.TrackRepository

class TrackRepositoryImpl(
    private val api: DeezerApiService,
) : TrackRepository {

    private var apiSongsCache: List<Track> = emptyList()

    override suspend fun getTracks(source: TrackSource): List<Track> {
        when (source) {
            //TODO methods?
            is TrackSource.Api -> {
                apiSongsCache = api.getChart().tracks.data.map { it.toDomain() }
                return apiSongsCache
            }

            is TrackSource.Local -> {
                return emptyList()
            }
        }
    }

    override suspend fun searchTracks(query: String, source: TrackSource): List<Track> {
        when (source) {
            //TODO methods?
            is TrackSource.Api -> {
                apiSongsCache = api.searchSongs(query).data.map { it.toDomain() }
                return apiSongsCache
            }

            is TrackSource.Local -> {
                return emptyList()
            }
        }
    }

    override suspend fun getCache(source: TrackSource): List<Track> {
        return when (source) {
            is TrackSource.Api -> apiSongsCache
            is TrackSource.Local -> emptyList()
        }
    }

    override suspend fun getTrack(songId: Long, source: TrackSource): Track {
        return when (source) {
            is TrackSource.Api -> api.getSong(query = songId.toString()).toDomain()
            is TrackSource.Local -> Track(
                id = 4680,
                title = "nibh",
                preview = "impetus",
                artist = "diam",
                albumUrl = "https://duckduckgo.com/?q=esse"
            )
        }
    }
}
