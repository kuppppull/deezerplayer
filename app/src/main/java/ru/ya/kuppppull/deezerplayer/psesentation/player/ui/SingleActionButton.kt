package ru.ya.kuppppull.deezerplayer.psesentation.player.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SingleActionButton(
    @DrawableRes iconRes: Int,
    size: Dp = 48.dp,
    onClick: () -> Unit = {},
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            painterResource(iconRes),
            contentDescription = "Next",
            modifier = Modifier.size(size)
        )
    }
}

fun formatTime(ms: Int): String {
    val minutes = (ms / 1000) / 60
    val seconds = (ms / 1000) % 60
    return String.format("%02d:%02d", minutes, seconds)
}