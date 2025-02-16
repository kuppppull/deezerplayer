package ru.ya.kuppppull.deezerplayer.psesentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.ya.kuppppull.deezerplayer.core.ui.widgets.BottomNavBar
import ru.ya.kuppppull.deezerplayer.psesentation.downloaded_tracks.navigation.DownloadedTracksBottomMenuItem
import ru.ya.kuppppull.deezerplayer.psesentation.downloaded_tracks.navigation.downloads
import ru.ya.kuppppull.deezerplayer.psesentation.player.navigation.PlayerDestination
import ru.ya.kuppppull.deezerplayer.psesentation.player.navigation.player
import ru.ya.kuppppull.deezerplayer.psesentation.tracks_from_api.navigation.TracksFromApiBottomMenuItem
import ru.ya.kuppppull.deezerplayer.psesentation.tracks_from_api.navigation.TracksFromApiDestination
import ru.ya.kuppppull.deezerplayer.psesentation.tracks_from_api.navigation.api

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController()
) {


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val destinations = listOf(
        TracksFromApiBottomMenuItem(),
        DownloadedTracksBottomMenuItem()
    )

    val navOptions = NavOptions.Builder()
        .setRestoreState(true)
        .build()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentDestination = currentRoute,
                destinations = destinations,
                onNavigate = { route ->
                    navController.navigate(route, navOptions)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = TracksFromApiDestination
            ) {

                api(
                    onNavigateToPlayer = {
                        navController.navigate(
                            PlayerDestination,
                            navOptions
                        )
                    }
                )
                downloads(
                    onNavigateToPlayer = {
                        navController.navigate(
                            PlayerDestination,
                            navOptions
                        )
                    }
                )

                player()
            }
        }
    }
}