package ru.ya.kuppppull.deezerplayer.psesentation.player.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import ru.ya.kuppppull.deezerplayer.di.DiProvider
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.psesentation.player.PlayerScreen
import ru.ya.kuppppull.deezerplayer.psesentation.player.PlayerViewModel

@Serializable
data class PlayerDestination(
    val trackId: Long,
    val source: String
)

fun NavGraphBuilder.player() {
    composable<PlayerDestination> { navBackStackEntry ->
        val playerDestination =
            navBackStackEntry.toRoute<PlayerDestination>()

        val trackId = playerDestination.trackId
        val source = TrackSource.fromJson(playerDestination.source)
        val viewModel = viewModel<PlayerViewModel>(
            factory = DiProvider.di.PlayerViewModelFactory().create(trackId, source)
        )

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        PlayerScreen(
            uiState = uiState,
            source = source,
            onTogglePlayPause = viewModel::togglePlayPause,
            onSeekTo = viewModel::seekTo,
            onNextTrack = viewModel::playNext,
            onPrevTrack = viewModel::playPrev,
            onSaveTrack = viewModel::saveSong
        )
    }
}
