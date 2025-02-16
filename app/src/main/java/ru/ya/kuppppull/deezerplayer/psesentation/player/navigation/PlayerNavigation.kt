package ru.ya.kuppppull.deezerplayer.psesentation.player.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.psesentation.player.PlayerScreen
import ru.ya.kuppppull.deezerplayer.psesentation.player.PlayerUiState

@Serializable
data object PlayerDestination

fun NavGraphBuilder.player() {
    composable<PlayerDestination> { navBackStackEntry ->
        PlayerScreen(
            uiState = PlayerUiState(
                isPlaying = false,
                currentPosition = 6183,
                trackDuration = 3339,
                trackPreview = "per",
                trackAlbumUrl = "http://www.bing.com/search?q=veritus",
                trackTitle = "option",
                trackArtist = "maiorum",
                trackId = 8147
            ),
            source = TrackSource.Local,
            onTogglePlayPause = {},
            onSeekTo = {},
            onNextTrack = {},
            onPrevTrack = {},
            onSaveTrack = {}
        )
    }
}
