package ru.ya.kuppppull.deezerplayer.psesentation.tracks_from_api

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.ya.kuppppull.deezerplayer.core.ui.widgets.SearchBar
import ru.ya.kuppppull.deezerplayer.core.ui.widgets.SongItem


@Composable
fun TracksFromApiScreen(
    uiState: TracksUiState,
    onSearch: (String) -> Unit,
    onLoad: () -> Unit,
    onChangeSearchQuery: (String) -> Unit,
    onNavigateToPlayer: (Long) -> Unit,
) {

    val listState = rememberLazyListState()

    LaunchedEffect(uiState.searchQuery) {
        if (uiState.searchQuery.isNotEmpty()) {
            onSearch(uiState.searchQuery)
        } else {
            onLoad()
        }
    }

    Column {
        SearchBar(query = uiState.searchQuery, onQueryChange = { onChangeSearchQuery(it) })

        if (uiState.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else if (uiState.error != null) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Error: ${uiState.error}")
            }
        } else {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                items(uiState.tracks) { song ->
                    SongItem(
                        track = song,
                        onNavigateToPlayer = onNavigateToPlayer
                    )
                }
            }
        }
    }
}