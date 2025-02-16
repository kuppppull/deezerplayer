package ru.ya.kuppppull.deezerplayer.domain.usecase

import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.domain.repository.TrackRepository
import javax.inject.Inject

class GetCacheUseCase @Inject constructor(private val repository: TrackRepository) {
    suspend fun execute(source: TrackSource) = repository.getCache(source)
}