package ru.ya.kuppppull.deezerplayer.psesentation.player.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SliderWithTime(
    currentPosition: Int,
    trackDuration: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier

) {
    Slider(
        value = currentPosition.toFloat(),
        onValueChange = { onValueChange(it.toInt()) },
        valueRange = 0f..trackDuration.toFloat(),
        modifier = modifier.fillMaxWidth()
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(formatTime(currentPosition))
        Text(formatTime(trackDuration))
    }
}
