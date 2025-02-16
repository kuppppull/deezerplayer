package ru.ya.kuppppull.deezerplayer.psesentation.downloaded_tracks.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.ya.kuppppull.deezerplayer.R
import ru.ya.kuppppull.deezerplayer.core.navigation.BottomNavigationItem
import ru.ya.kuppppull.deezerplayer.psesentation.downloaded_tracks.DownloadedTracksScreen

@Serializable
object DownloadedTracksDestination

data class DownloadedTracksBottomMenuItem(
    override val iconId: Int = R.drawable.ic_download,
    override val titleId: Int = R.string.downloaded,
    override val route: Any = DownloadedTracksDestination
) : BottomNavigationItem

fun NavGraphBuilder.downloads(
){
    composable<DownloadedTracksDestination> {
        DownloadedTracksScreen()
    }
}