package ru.ya.kuppppull.deezerplayer.psesentation.player.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.ya.kuppppull.deezerplayer.R
import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.psesentation.player.PlayerUiState


@Composable
fun PlayerScreenPortrait(
    uiState: PlayerUiState,
    source: TrackSource,
    onSeekTo: (Int) -> Unit,
    onTogglePlayPause: () -> Unit,
    onNextTrack: () -> Unit,
    onPrevTrack: () -> Unit,
    onSaveTrack: (Track) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AlbumCover(albumUrl = uiState.trackAlbumUrl, trackTitle = uiState.trackTitle)
        Spacer(modifier = Modifier.height(16.dp))
        TrackInfo(
            title = uiState.trackTitle,
            artistName = uiState.trackArtist
        )
        Spacer(modifier = Modifier.height(16.dp))

        SliderWithTime(
            currentPosition = uiState.currentPosition,
            trackDuration = uiState.trackDuration,
            onValueChange = onSeekTo
        )

        Spacer(modifier = Modifier.height(16.dp))

        Controls(
            isPlaying = uiState.isPlaying,
            onPrev = onPrevTrack,
            onPlayPause = onTogglePlayPause,
            onNext = onNextTrack
        )

        Spacer(modifier = Modifier.height(24.dp))

        when (source) {
            is TrackSource.Api -> {
                SingleActionButton(
                    iconRes = R.drawable.ic_download,
                ) {
                    onSaveTrack(
                        Track(
                            id = uiState.trackId,
                            preview = uiState.trackPreview,
                            artist = uiState.trackArtist,
                            title = uiState.trackTitle,
                            albumUrl = ""
                        )
                    )
                }
            }

            is TrackSource.Local -> {
                SingleActionButton(
                    iconRes = R.drawable.ic_delete,
                )
            }
        }
    }
}