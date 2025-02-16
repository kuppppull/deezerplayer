package ru.ya.kuppppull.deezerplayer.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ya.kuppppull.deezerapi.data.FilesScanner
import ru.ya.kuppppull.deezerplayer.data.mappers.toDomain
import ru.ya.kuppppull.deezerplayer.data.network.api.DeezerApiService
import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.domain.repository.TrackRepository

class TrackRepositoryImpl(
    private val api: DeezerApiService,
    private val fileManager: FilesScanner
) : TrackRepository {

    private var apiSongsCache: List<Track> = emptyList()
    private var storageSongsCache: List<Track> = emptyList()

    override suspend fun getTracks(source: TrackSource): List<Track> {
        when(source) {
            //TODO methods?
            is TrackSource.Api -> {
                apiSongsCache = api.getChart().tracks.data.map { it.toDomain() }
                return apiSongsCache
            }
            is TrackSource.Local -> {
                storageSongsCache = withContext(Dispatchers.IO) {
                    fileManager.getFiles()
                }
                return storageSongsCache
            }
        }
    }

    override suspend fun searchTracks(query: String, source: TrackSource): List<Track> {
        when(source) {
            //TODO methods?
            is TrackSource.Api -> {
                apiSongsCache = api.searchSongs(query).data.map { it.toDomain() }
                return apiSongsCache
            }
            is TrackSource.Local -> {
                storageSongsCache = getTracks(source = source).filter {
                    it.artist.lowercase().contains(query.lowercase())
                            || it.title.lowercase().contains(query.lowercase())
                }

                return storageSongsCache
            }
        }
    }

    override suspend fun getCache(source: TrackSource): List<Track> {
        return when (source) {
            is TrackSource.Api -> apiSongsCache
            is TrackSource.Local -> storageSongsCache
        }
    }

    override suspend fun getTrack(songId: Long, source: TrackSource): Track {
        return when (source) {
            is TrackSource.Api -> api.getSong(query = songId.toString()).toDomain()
            is TrackSource.Local -> fileManager.getFile(songId)
        }
    }
}
