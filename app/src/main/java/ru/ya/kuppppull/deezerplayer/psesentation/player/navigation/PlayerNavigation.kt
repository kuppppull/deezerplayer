package ru.ya.kuppppull.deezerplayer.psesentation.player.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.ya.kuppppull.deezerplayer.psesentation.player.PlayerScreen

@Serializable
data object PlayerDestination

fun NavGraphBuilder.player() {
    composable<PlayerDestination> { navBackStackEntry ->
        PlayerScreen()
    }
}
