package ru.ya.kuppppull.deezerplayer.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChartResponse(
    val tracks: TrackData
)

@Serializable
data class SearchResponse(
    val data: List<SongNetwork>
)

@Serializable
data class TrackData(
    val data: List<SongNetwork>
)

@Serializable
data class SongNetwork(
    val id: Long,
    val title: String,
    val preview: String,
    val artist: Artist,
    val album: Album
)

@Serializable
data class Artist(
    val name: String
)

@Serializable
data class Album(
    @SerialName("cover_medium") val coverMedium: String
)
