package ru.ya.kuppppull.deezerplayer.core.ui.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.ya.kuppppull.deezerplayer.core.navigation.BottomNavigationItem

@Composable
fun BottomNavBar(
    currentDestination: String?,
    destinations: List<BottomNavigationItem>,
    onNavigate: (route: Any) -> Unit,
) {
    NavigationBar(
        modifier = Modifier.height(72.dp)
    ) {
        destinations.forEachIndexed { index, item ->

            val selected = currentDestination == item.route::class.qualifiedName

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.iconId),
                        contentDescription = stringResource(item.titleId)
                    )
                },
                //label = { Text( text = stringResource(item.titleId)) },
                selected = selected,
                onClick = {
                    onNavigate(item.route)
                }
            )
        }
    }
}