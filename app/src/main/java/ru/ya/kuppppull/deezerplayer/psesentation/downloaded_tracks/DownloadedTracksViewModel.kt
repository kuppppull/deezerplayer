package ru.ya.kuppppull.deezerplayer.psesentation.downloaded_tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ya.kuppppull.deezerplayer.domain.models.Track
import ru.ya.kuppppull.deezerplayer.domain.models.TrackSource
import ru.ya.kuppppull.deezerplayer.domain.usecase.GetTracksUseCase
import ru.ya.kuppppull.deezerplayer.domain.usecase.SearchTracksUseCase
import javax.inject.Inject

data class DownloadUiState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val tracks: List<Track> = emptyList(),
    val error: String? = null
)

class DownloadedTracksViewModel(
    private val getTracksUseCase: GetTracksUseCase,
    private val searchTracksUseCase: SearchTracksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DownloadUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadSongs()
    }

    fun onChangeSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun loadSongs() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val songs = getTracksUseCase.execute(TrackSource.Local)
                _uiState.value = DownloadUiState(tracks = songs)
            } catch (e: Exception) {
                _uiState.value =
                    _uiState.value.copy(error = e.localizedMessage)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun searchSongs(searchQuery: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {
                val songs = searchTracksUseCase.execute(searchQuery, source = TrackSource.Local)
                _uiState.value = _uiState.value.copy(tracks = songs)
            } catch (e: Exception) {
                _uiState.value =
                    _uiState.value.copy(error = e.localizedMessage)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    class DownloadedTracksViewModelFactory @Inject constructor(
        private val getTracksUseCase: GetTracksUseCase,
        private val searchTracksUseCase: SearchTracksUseCase
    ) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras,
        ): T {
            return DownloadedTracksViewModel(
                getTracksUseCase = getTracksUseCase,
                searchTracksUseCase = searchTracksUseCase
            ) as T
        }
    }
}