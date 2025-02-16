package ru.ya.kuppppull.deezerplayer.psesentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.ya.kuppppull.deezerplayer.core.ui.theme.DeezerplayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DeezerplayerTheme {
                MainNavHost()
            }
        }
    }
}
