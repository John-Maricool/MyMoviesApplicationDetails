package com.maricoolsapps.sportsapplication.ui.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricoolsapps.sportsapplication.data.models.Cast
import com.maricoolsapps.sportsapplication.data.models.Movie
import com.maricoolsapps.sportsapplication.data.models.Video
import com.maricoolsapps.sportsapplication.data.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel
@Inject constructor(val repository: MovieListRepository) : ViewModel() {

    val isLoading = mutableStateOf(true)
    val movie = mutableStateOf(Movie())
    val casts: MutableState<List<Cast>?> = mutableStateOf(null)
    val videos: MutableState<List<Video>?> = mutableStateOf(null)

    var isConnected = false

    fun loadData(id: Long) {
        isConnected = repository.isConnected()
        if (repository.isConnected())
            getMovieDetail(id)
    }

    private fun getMovieDetail(id: Long) {
        viewModelScope.launch {
            movie.value = repository.getMovie(id)
            casts.value = repository.getCredits(id).casts
            videos.value = repository.getVideos(id)
            isLoading.value = false
        }
    }
}