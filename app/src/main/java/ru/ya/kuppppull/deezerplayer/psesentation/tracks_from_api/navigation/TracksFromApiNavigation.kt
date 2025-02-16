package ru.ya.kuppppull.deezerplayer.psesentation.tracks_from_api.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.ya.kuppppull.deezerplayer.R
import ru.ya.kuppppull.deezerplayer.core.navigation.BottomNavigationItem
import ru.ya.kuppppull.deezerplayer.di.DiProvider
import ru.ya.kuppppull.deezerplayer.psesentation.tracks_from_api.TracksFromApiScreen
import ru.ya.kuppppull.deezerplayer.psesentation.tracks_from_api.TracksFromApiViewModel

@Serializable
object TracksFromApiDestination

data class TracksFromApiBottomMenuItem(
    override val iconId: Int = R.drawable.ic_deezer,
    override val titleId: Int = R.string.api,
    override val route: Any = TracksFromApiDestination
) : BottomNavigationItem

fun NavGraphBuilder.api(
    onNavigateToPlayer: (Long) -> Unit
) {
    composable<TracksFromApiDestination> {
        val viewModel = viewModel<TracksFromApiViewModel>(
            factory = DiProvider.di.TracksFromApiViewModelFactory()
        )

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        TracksFromApiScreen(
            uiState = uiState,
            onLoad = viewModel::loadSongs,
            onSearch = viewModel::searchSongs,
            onChangeSearchQuery = viewModel::onChangeSearchQuery,
            onNavigateToPlayer = onNavigateToPlayer
        )
    }
}