package ru.ya.kuppppull.deezerplayer.domain.usecase

import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.domain.repository.TrackRepository
import javax.inject.Inject

class GetSongUseCase @Inject constructor(private val repository: TrackRepository) {
    suspend fun execute(songId: Long, source: TrackSource) = repository.getTrack(songId = songId, source = source)
}