package com.maricoolsapps.sportsapplication.ui.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricoolsapps.sportsapplication.data.models.Cast
import com.maricoolsapps.sportsapplication.data.models.TvShowDetails
import com.maricoolsapps.sportsapplication.data.models.Video
import com.maricoolsapps.sportsapplication.data.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailsViewModel
@Inject constructor(val repository: MovieListRepository) : ViewModel() {

    val isLoading = mutableStateOf(true)
    val tvDetails = mutableStateOf(TvShowDetails())
    val casts: MutableState<List<Cast>?> = mutableStateOf(null)
    val videos: MutableState<List<Video>?> = mutableStateOf(null)
    var isConnected = false

    fun loadData(id: Long) {
        isConnected = repository.isConnected()
        if (repository.isConnected())
            getTvDetails(id)
    }

    private fun getTvDetails(id: Long) {
        viewModelScope.launch {
            tvDetails.value = repository.getTvDetails(id)
            casts.value = repository.getTvCredits(id).casts
            videos.value = repository.getTvVideos(id)
            isLoading.value = false

        }
    }
}