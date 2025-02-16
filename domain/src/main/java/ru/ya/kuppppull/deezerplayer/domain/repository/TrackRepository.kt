package ru.ya.kuppppull.deezerplayer.domain.repository

import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource

interface TrackRepository {

    suspend fun getTracks(source: TrackSource): List<Track>
    suspend fun searchTracks(query: String, source: TrackSource): List<Track>
    suspend fun getCache(source: TrackSource): List<Track>
    suspend fun getTrack(songId: Long, source: TrackSource): Track

}