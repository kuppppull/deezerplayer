package ru.ya.kuppppull.deezerplayer.core.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.ya.kuppppull.deezerplayer.R
import ru.ya.kuppppull.deezerplayer.domain.models.Track

@Composable
fun SongItem(
    track: Track,
    onNavigateToPlayer: (Long) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNavigateToPlayer(track.id)
            }
    ) {
        AsyncImage(
            model = track.albumUrl,
            contentDescription = track.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            placeholder = painterResource(R.drawable.place_holder),
            error = painterResource(R.drawable.place_holder),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(track.artist)
            Text(track.title)
        }
    }

    Spacer(Modifier.height(16.dp))
}