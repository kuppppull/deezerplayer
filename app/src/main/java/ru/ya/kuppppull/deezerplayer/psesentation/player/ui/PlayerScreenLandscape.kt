package ru.ya.kuppppull.deezerplayer.psesentation.player.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.ya.kuppppull.deezerplayer.R
import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.psesentation.player.PlayerUiState

@Composable
fun PlayerScreenLandscape(
    uiState: PlayerUiState,
    source: TrackSource,
    onSeekTo: (Int) -> Unit,
    onTogglePlayPause: () -> Unit,
    onNextTrack: () -> Unit,
    onPrevTrack: () -> Unit,
    onSaveTrack: (Track) -> Unit,
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AlbumCover(
                albumUrl = uiState.trackAlbumUrl,
                trackTitle = uiState.trackTitle,
                size = 200.dp
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                ) {
                    TrackInfo(
                        title = uiState.trackTitle,
                        artistName = uiState.trackArtist
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End),
                    horizontalAlignment = Alignment.End
                ) {
                    when (source) {
                        is TrackSource.Api -> {
                            SingleActionButton(
                                iconRes = R.drawable.ic_download,
                                size = 32.dp
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
                                size = 32.dp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            SliderWithTime(
                currentPosition = uiState.currentPosition,
                trackDuration = uiState.trackDuration,
                onValueChange = onSeekTo
            )

            Controls(
                isPlaying = uiState.isPlaying,
                onPrev = onPrevTrack,
                onPlayPause = onTogglePlayPause,
                onNext = onNextTrack
            )
        }
    }
}