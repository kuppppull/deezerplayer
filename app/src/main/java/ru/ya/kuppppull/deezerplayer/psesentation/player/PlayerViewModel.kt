package ru.ya.kuppppull.deezerplayer.psesentation.player

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import com.google.common.util.concurrent.MoreExecutors
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.ya.kuppppull.deezerplayer.di.DiProvider
import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.domain.usecase.GetCacheUseCase
import ru.ya.kuppppull.deezerplayer.domain.usecase.GetSongUseCase
import ru.ya.kuppppull.deezerplayer.domain.usecase.SaveTrackUseCase

data class PlayerUiState(
    val isPlaying: Boolean = false,
    val currentPosition: Int = 0,
    val trackDuration: Int = 0,
    val trackPreview: String = "",
    val trackAlbumUrl: String = "",
    val trackTitle: String = "",
    val trackArtist: String = "",
    val trackId: Long = 0L
)

class PlayerViewModel(
    private val getSongUseCase: GetSongUseCase,
    private val getCacheUseCase: GetCacheUseCase,
    private val saveTrackUseCase: SaveTrackUseCase,
    private val trackId: Long,
    private val source: TrackSource,
) : ViewModel() {

    private var mediaController: MediaController? by mutableStateOf(null)


    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState = _uiState.asStateFlow()

    private val playerListener = @UnstableApi
    object : Player.Listener {
        @UnstableApi
        override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {
            super.onMediaMetadataChanged(mediaMetadata)

            _uiState.update {
                it.copy(
                    trackTitle = mediaMetadata.title.toString(),
                    trackArtist = mediaMetadata.artist.toString(),
                    trackDuration = mediaMetadata.durationMs?.toInt() ?: 0,
                    trackAlbumUrl = mediaMetadata.artworkUri.toString(),
                    trackPreview = mediaController?.currentMediaItem?.localConfiguration?.uri.toString()
                )
            }
        }


        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            _uiState.update {
                it.copy(
                    isPlaying = isPlaying
                )
            }
        }


        override fun onEvents(player: Player, events: Player.Events) {
            super.onEvents(player, events)
            viewModelScope.launch {
                while (player.isPlaying) {
                    _uiState.update {
                        it.copy(
                            trackDuration = player.duration.toInt(),
                            currentPosition = player.currentPosition.toInt()
                        )
                    }
                    delay(500)
                }
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
//            when (playbackState) {
//                Player.STATE_IDLE -> {
//                    _uiState.update {
//                        it.copy(
//                            isPlayerReady = false
//                        )
//                    }
//                }
//
//                Player.STATE_READY -> {
//                    _musicState.update {
//                        it.copy(
//                            isPlayerReady = true
//                        )
//                    }
//                }
//
//                else -> {
//                    _musicState.update {
//                        it.copy(
//                            isPlayerReady = true
//                        )
//                    }
//                }
//            }
        }
    }


    init {
        viewModelScope.launch {

            val playlist = getCacheUseCase.execute(source)
            val mediaItems = playlist.map { it.toMediaItem() }
            val song = getSongUseCase.execute(trackId, source)
            _uiState.value = _uiState.value.copy(
                trackId = song.id,
                trackPreview = song.preview,
                trackAlbumUrl = song.albumUrl,
                trackTitle = song.title,
                trackArtist = song.artist,
            )

            val index = playlist.indexOfFirst { it.id == song.id }


            try {
                DiProvider.di.getMediaController()
                    .addListener(
                        {
                            mediaController = DiProvider.di.getMediaController().get()
                            mediaController?.addListener(playerListener)
                            mediaController?.setMediaItems(mediaItems, index, 0)
                            mediaController?.prepare()
                            mediaController?.play()

                        },
                        MoreExecutors.directExecutor()
                    )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveSong(track: Track) {
        viewModelScope.launch {
            saveTrackUseCase.execute(track)
        }
    }

    fun togglePlayPause() {
        if (uiState.value.isPlaying) {
            mediaController!!.stop()
            _uiState.update {
                it.copy(
                    isPlaying = false
                )
            }
        } else {
            mediaController?.play()
            _uiState.update {
                it.copy(
                    isPlaying = true
                )
            }
        }
    }

    fun seekTo(position: Int) {
        mediaController?.seekTo(position.toLong())
        _uiState.value = _uiState.value.copy(
            currentPosition = position
        )
    }

    fun playNext() {
        viewModelScope.launch {
            mediaController?.prepare()
            mediaController?.seekToNextMediaItem()
        }
    }

    fun playPrev() {
        viewModelScope.launch {
            mediaController?.prepare()
            mediaController?.seekToPreviousMediaItem()
        }
    }
}

fun Track.toMediaItem(): MediaItem = MediaItem.Builder()
    .setUri(this.preview)
    .setMediaMetadata(
        MediaMetadata
            .Builder()
            .setIsPlayable(true)
            .setTitle(this.title)
            .setArtist(this.artist)
            .setArtworkUri(Uri.parse(this.albumUrl))
            .build()
    )
    .build()


class PlayerViewModelFactory @AssistedInject constructor(
    private val getSongUseCase: GetSongUseCase,
    private val getCacheUseCase: GetCacheUseCase,
    private val saveTrackUseCase: SaveTrackUseCase,
    @Assisted("trackId") private val trackId: Long,
    @Assisted("source") private val source: TrackSource,
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return PlayerViewModel(
            getSongUseCase = getSongUseCase,
            getCacheUseCase = getCacheUseCase,
            saveTrackUseCase = saveTrackUseCase,
            trackId = trackId,
            source = source
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("trackId") trackId: Long,
            @Assisted("source") source: TrackSource,
        ): PlayerViewModelFactory
    }

}