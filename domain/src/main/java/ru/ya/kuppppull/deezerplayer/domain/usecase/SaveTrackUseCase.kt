package ru.ya.kuppppull.deezerplayer.domain.usecase

import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.repository.TrackRepository
import javax.inject.Inject

class SaveTrackUseCase @Inject constructor(private val repository: TrackRepository) {
    suspend fun execute(track: Track) = repository.saveTrack(track = track)
}