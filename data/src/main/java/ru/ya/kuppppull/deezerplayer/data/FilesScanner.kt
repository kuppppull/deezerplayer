package ru.ya.kuppppull.deezerplayer.data

import android.content.Context
import ru.ya.kuppppull.deezerplayer.data.mappers.toSong
import ru.ya.kuppppull.deezerplayer.domain.models.Track

class FilesScanner(private val context: Context) {
    fun getFiles(): List<Track> {
        val mp3Files =
            context.filesDir.listFiles { file -> file.extension == "mp3" } ?: emptyArray()
        return mp3Files.map {
            it.toSong(context)
        }
    }

    fun getFile(trackId: Long): Track {
        val mp3Files =
            context.filesDir.listFiles { file -> file.extension == "mp3" } ?: emptyArray()
        val track = mp3Files.map {
            it.toSong(context)
        }.find { it.id ==  trackId} ?: Track(
            id = 0,
            title = "file not found",
            preview = "",
            artist = "none",
            albumUrl = ""
        )

        return track
    }
}