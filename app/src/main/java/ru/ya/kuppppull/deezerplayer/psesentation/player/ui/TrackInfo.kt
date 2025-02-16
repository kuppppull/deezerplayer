package ru.ya.kuppppull.deezerplayer.psesentation.player.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TrackInfo(
    title: String,
    artistName: String
) {

    Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    Text(artistName, fontSize = 18.sp, color = Color.Gray)

}