package ru.ya.kuppppull.deezerplayer.psesentation.player.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.ya.kuppppull.deezerplayer.R


@Composable
fun Controls(
    isPlaying: Boolean,
    onPrev: () -> Unit,
    onPlayPause: () -> Unit,
    onNext: () -> Unit,
    onPlayPauseSize: Dp = 80.dp,
    onPrevNextSize: Dp = 48.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onPrev() }) {
            Icon(
                painterResource(R.drawable.ic_previous),
                contentDescription = stringResource(R.string.previous),
                modifier = Modifier.size(onPrevNextSize)
            )
        }

        IconButton(onClick = { onPlayPause() }, modifier = Modifier.size(onPlayPauseSize)) {
            Icon(
                painter = if (isPlaying) painterResource(R.drawable.ic_pause)
                else painterResource(R.drawable.ic_play),
                contentDescription = stringResource(R.string.play_pause),
                modifier = Modifier.size(onPlayPauseSize)
            )
        }

        IconButton(onClick = { onNext() }) {
            Icon(
                painterResource(R.drawable.ic_next),
                contentDescription = stringResource(R.string.next),
                modifier = Modifier.size(onPrevNextSize)
            )
        }
    }
}
