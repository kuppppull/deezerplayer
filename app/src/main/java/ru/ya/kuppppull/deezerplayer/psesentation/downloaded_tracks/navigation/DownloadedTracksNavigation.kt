package ru.ya.kuppppull.deezerplayer.psesentation.downloaded_tracks.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.ya.kuppppull.deezerplayer.R
import ru.ya.kuppppull.deezerplayer.core.navigation.BottomNavigationItem
import ru.ya.kuppppull.deezerplayer.di.DiProvider
import ru.ya.kuppppull.deezerplayer.psesentation.downloaded_tracks.DownloadedTracksScreen
import ru.ya.kuppppull.deezerplayer.psesentation.downloaded_tracks.DownloadedTracksViewModel

@Serializable
object DownloadedTracksDestination

data class DownloadedTracksBottomMenuItem(
    override val iconId: Int = R.drawable.ic_download,
    override val titleId: Int = R.string.downloaded,
    override val route: Any = DownloadedTracksDestination
) : BottomNavigationItem

fun NavGraphBuilder.downloads(
    onNavigateToPlayer: (Long) -> Unit
){
    composable<DownloadedTracksDestination> {
        val viewModel = viewModel<DownloadedTracksViewModel>(
            factory = DiProvider.di.DownloadedTracksViewModelFactory()
        )

        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        DownloadedTracksScreen(
            uiState = uiState,
            onLoad = viewModel::loadSongs,
            onSearch = viewModel::searchSongs,
            onChangeSearchQuery = viewModel::onChangeSearchQuery,
            onNavigateToPlayer = onNavigateToPlayer
        )
    }
}