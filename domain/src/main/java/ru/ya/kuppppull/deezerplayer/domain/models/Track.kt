package ru.ya.kuppppull.deezerplayer.domain.models

data class Track(
    val id: Long,
    val title: String,
    val preview: String,
    val artist: String,
    val albumUrl: String
)