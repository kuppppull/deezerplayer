package ru.ya.kuppppull.deezerplayer.data.mappers

import android.content.Context
import androidx.core.content.FileProvider
import ru.ya.kuppppull.deezerplayer.data.network.model.SongNetwork
import ru.ya.kuppppull.deezerplayer.data.utils.decodeBase64
import ru.ya.kuppppull.deezerplayer.domain.models.Track
import java.io.File


fun SongNetwork.toDomain(): Track {
    return Track(
        id = this.id,
        title = this.title,
        preview = this.preview,
        artist = this.artist.name,
        albumUrl = this.album.coverMedium
    )
}


fun File.toSong(context: Context): Track {
    val nameWithoutExtension = this.name.removeSuffix(".mp3")
    val parts = nameWithoutExtension.split("%%%")

    val id = parts[0]
    val artist = parts[1]
    val title = parts[2]

    val decodedArtist = decodeBase64(artist)
    val decodedTitle = decodeBase64(title)

    val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", this)

    return Track(
        id = id.toLong(),
        title = decodedTitle,
        preview = uri.toString(),
        artist = decodedArtist,
        albumUrl = "",
    )
}