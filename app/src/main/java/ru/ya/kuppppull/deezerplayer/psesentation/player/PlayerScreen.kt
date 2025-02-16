package ru.ya.kuppppull.deezerplayer.psesentation.player

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.psesentation.player.ui.PlayerScreenLandscape
import ru.ya.kuppppull.deezerplayer.psesentation.player.ui.PlayerScreenPortrait

@Composable
fun PlayerScreen(
    uiState: PlayerUiState,
    source: TrackSource,
    onSeekTo: (Int) -> Unit,
    onTogglePlayPause: () -> Unit,
    onNextTrack: () -> Unit,
    onPrevTrack: () -> Unit,
    onSaveTrack: (Track) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val isPortrait = remember(configuration) {
        configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    if (isPortrait) {
        PlayerScreenPortrait(
            uiState = uiState,
            source = source,
            onSeekTo = onSeekTo,
            onTogglePlayPause = onTogglePlayPause,
            onNextTrack = onNextTrack,
            onPrevTrack = onPrevTrack,
            onSaveTrack = onSaveTrack
        )
    } else {
        PlayerScreenLandscape(
            uiState = uiState,
            source = source,
            onSeekTo = onSeekTo,
            onTogglePlayPause = onTogglePlayPause,
            onNextTrack = onNextTrack,
            onPrevTrack = onPrevTrack,
            onSaveTrack = onSaveTrack
        )
    }
}

@Composable
@Preview(
    name = "Landscape Preview",
    showBackground = true,
    widthDp = 640,
    heightDp = 360
)
fun PlayerScreenPreview() {
    PlayerScreen(
        uiState = PlayerUiState(
            isPlaying = false,
            currentPosition = 8246,
            trackDuration = 5866,
            trackPreview = "maluisset",
            trackAlbumUrl = "https://duckduckgo.com/?q=semper",
            trackTitle = "imperdiet",
            trackArtist = "maximus"
        ),
        onSeekTo = {}, onTogglePlayPause = {}, onNextTrack = {},
        source = TrackSource.Local, onSaveTrack = {}, onPrevTrack = {},
    )
}
