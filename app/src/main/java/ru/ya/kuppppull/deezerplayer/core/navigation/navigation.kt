package ru.ya.kuppppull.deezerplayer.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface BottomNavigationItem {
    @get:DrawableRes
    val iconId: Int
    @get:StringRes
    val titleId: Int
    val route: Any
}