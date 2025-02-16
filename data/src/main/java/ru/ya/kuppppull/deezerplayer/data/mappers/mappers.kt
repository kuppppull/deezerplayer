package ru.ya.kuppppull.deezerplayer.data.mappers

import ru.ya.kuppppull.deezerplayer.data.network.model.SongNetwork
import ru.ya.kuppppull.deezerplayer.domain.models.Track


fun SongNetwork.toDomain(): Track {
    return Track(
        id = this.id,
        title = this.title,
        preview = this.preview,
        artist = this.artist.name,
        albumUrl = this.album.coverMedium
    )
}