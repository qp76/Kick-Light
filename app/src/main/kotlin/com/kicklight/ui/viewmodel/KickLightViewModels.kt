package com.kicklight.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kicklight.data.api.ChannelResponse
import com.kicklight.data.api.ClipResponse
import com.kicklight.data.api.StreamResponse
import com.kicklight.data.api.VideoResponse
import com.kicklight.data.api.CategoryResponse
import com.kicklight.data.repository.StreamRepository
import com.kicklight.data.repository.ChannelRepository
import com.kicklight.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ==================== Sealed Classes for UI State ====================

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val exception: Throwable) : UiState<Nothing>()
}

// ==================== Home ViewModel ====================

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val streamRepository: StreamRepository
) : ViewModel() {

    private val _liveStreams = MutableStateFlow<UiState<List<StreamResponse>>>(UiState.Loading)
    val liveStreams: StateFlow<UiState<List<StreamResponse>>> = _liveStreams.asStateFlow()

    private val _categories = MutableStateFlow<List<CategoryResponse>>(emptyList())
    val categories: StateFlow<List<CategoryResponse>> = _categories.asStateFlow()

    init {
        loadLiveStreams()
    }

    fun loadLiveStreams(page: Int = 1) {
        viewModelScope.launch {
            _liveStreams.value = UiState.Loading
            streamRepository.getLiveStreams(page = page).collect { result ->
                _liveStreams.value = result.fold(
                    onSuccess = { response -> UiState.Success(response.data) },
                    onFailure = { exception -> UiState.Error(exception) }
                )
            }
        }
    }

    fun refreshStreams() {
        loadLiveStreams()
    }
}

// ==================== Search ViewModel ====================

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val channelRepository: ChannelRepository,
    private val streamRepository: StreamRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<UiState<SearchResultsUiState>>(UiState.Loading)
    val searchResults: StateFlow<UiState<SearchResultsUiState>> = _searchResults.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.isNotBlank()) {
            search(query)
        } else {
            _searchResults.value = UiState.Success(SearchResultsUiState())
        }
    }

    private fun search(query: String) {
        viewModelScope.launch {
            _searchResults.value = UiState.Loading
            searchRepository.search(query).collect { result ->
                _searchResults.value = result.fold(
                    onSuccess = { searchResponse ->
                        UiState.Success(
                            SearchResultsUiState(
                                channels = searchResponse.channels,
                                streams = searchResponse.streams,
                                categories = searchResponse.categories
                            )
                        )
                    },
                    onFailure = { exception -> UiState.Error(exception) }
                )
            }
        }
    }
}

data class SearchResultsUiState(
    val channels: List<ChannelResponse> = emptyList(),
    val streams: List<StreamResponse> = emptyList(),
    val categories: List<CategoryResponse> = emptyList()
)

// ==================== Channel ViewModel ====================

@HiltViewModel
class ChannelViewModel @Inject constructor(
    private val channelRepository: ChannelRepository
) : ViewModel() {

    private val _channel = MutableStateFlow<UiState<ChannelResponse>>(UiState.Loading)
    val channel: StateFlow<UiState<ChannelResponse>> = _channel.asStateFlow()

    private val _videos = MutableStateFlow<UiState<List<VideoResponse>>>(UiState.Loading)
    val videos: StateFlow<UiState<List<VideoResponse>>> = _videos.asStateFlow()

    private val _clips = MutableStateFlow<UiState<List<ClipResponse>>>(UiState.Loading)
    val clips: StateFlow<UiState<List<ClipResponse>>> = _clips.asStateFlow()

    fun loadChannel(slug: String) {
        viewModelScope.launch {
            _channel.value = UiState.Loading
            channelRepository.getChannel(slug).collect { result ->
                _channel.value = result.fold(
                    onSuccess = { UiState.Success(it) },
                    onFailure = { exception -> UiState.Error(exception) }
                )
            }
        }
    }

    fun loadChannelVideos(slug: String, page: Int = 1) {
        viewModelScope.launch {
            _videos.value = UiState.Loading
            channelRepository.getChannelVideos(slug, page).collect { result ->
                _videos.value = result.fold(
                    onSuccess = { response -> UiState.Success(response.data) },
                    onFailure = { exception -> UiState.Error(exception) }
                )
            }
        }
    }

    fun loadChannelClips(slug: String, page: Int = 1) {
        viewModelScope.launch {
            _clips.value = UiState.Loading
            channelRepository.getChannelClips(slug, page).collect { result ->
                _clips.value = result.fold(
                    onSuccess = { response -> UiState.Success(response.data) },
                    onFailure = { exception -> UiState.Error(exception) }
                )
            }
        }
    }
}

// ==================== Player ViewModel ====================

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val streamRepository: StreamRepository
) : ViewModel() {

    private val _currentStream = MutableStateFlow<UiState<StreamResponse>>(UiState.Loading)
    val currentStream: StateFlow<UiState<StreamResponse>> = _currentStream.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _isMiniPlayer = MutableStateFlow(false)
    val isMiniPlayer: StateFlow<Boolean> = _isMiniPlayer.asStateFlow()

    fun loadStream(streamId: String) {
        viewModelScope.launch {
            streamRepository.getStream(streamId).collect { result ->
                _currentStream.value = result.fold(
                    onSuccess = { UiState.Success(it) },
                    onFailure = { exception -> UiState.Error(exception) }
                )
            }
        }
    }

    fun togglePlayback() {
        _isPlaying.value = !_isPlaying.value
    }

    fun setMiniPlayer(enabled: Boolean) {
        _isMiniPlayer.value = enabled
    }
}

// ==================== Web ViewModel ====================

@HiltViewModel
class WebViewModel @Inject constructor() : ViewModel() {

    private val _webUrl = MutableStateFlow("https://kick.com")
    val webUrl: StateFlow<String> = _webUrl.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadUrl(url: String) {
        _webUrl.value = url
        _isLoading.value = true
    }

    fun onPageLoaded() {
        _isLoading.value = false
    }

    fun onPageFailed() {
        _isLoading.value = false
    }
}

// ==================== Settings ViewModel ====================

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val _darkMode = MutableStateFlow(true)
    val darkMode: StateFlow<Boolean> = _darkMode.asStateFlow()

    private val _videoQuality = MutableStateFlow("auto")
    val videoQuality: StateFlow<String> = _videoQuality.asStateFlow()

    private val _autoplay = MutableStateFlow(true)
    val autoplay: StateFlow<Boolean> = _autoplay.asStateFlow()

    fun setDarkMode(enabled: Boolean) {
        _darkMode.value = enabled
    }

    fun setVideoQuality(quality: String) {
        _videoQuality.value = quality
    }

    fun setAutoplay(enabled: Boolean) {
        _autoplay.value = enabled
    }
}
