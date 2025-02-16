package ru.ya.kuppppull.deezerplayer.psesentation.player.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.ya.kuppppull.deezerplayer.R

@Composable
fun AlbumCover(
    albumUrl: String,
    trackTitle: String,
    size: Dp = 250.dp,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = albumUrl,
        contentDescription = trackTitle,
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(16.dp)),
        placeholder = painterResource(R.drawable.place_holder),
        error = painterResource(R.drawable.place_holder),
        contentScale = ContentScale.Crop
    )
}
