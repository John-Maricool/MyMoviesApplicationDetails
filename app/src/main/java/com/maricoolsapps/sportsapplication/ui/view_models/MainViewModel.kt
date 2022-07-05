package com.maricoolsapps.sportsapplication.ui.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricoolsapps.sportsapplication.data.models.Movie
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.repository.MovieListRepository
import com.maricoolsapps.sportsapplication.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    val repository: MovieListRepository
) : ViewModel() {
    var popularMovieList: MutableState<List<MovieListItemModel>> = mutableStateOf(listOf())
    var inTheatreMovieList: MutableState<List<MovieListItemModel>> = mutableStateOf(listOf())
    var upcomingMovieList: MutableState<List<MovieListItemModel>> = mutableStateOf(listOf())
    var singleMovie: MutableState<Movie> = mutableStateOf(Movie())
    var isLoading: MutableState<Boolean> = mutableStateOf(true)
    var isConnected = false

    fun loadData() {
        isConnected = repository.isConnected()
        if (repository.isConnected())
            getMoviesFirstScreen()
    }

    private fun getMoviesFirstScreen() {
        try {
            viewModelScope.launch {
                singleMovie.value = repository.getSingleMovieId()
                val popular = repository.getMovieFromTypeSinglePage(Constants.POPULAR_MOVIES)
                val inTheatre = repository.getMovieFromTypeSinglePage(Constants.IN_THEATRE_MOVIES)
                val upcoming = repository.getMovieFromTypeSinglePage(Constants.UPCOMING_MOVIES)
                popularMovieList.value = popular
                upcomingMovieList.value = upcoming
                inTheatreMovieList.value = inTheatre
                isLoading.value = false
            }
        } catch (e: Exception) {
            isLoading.value = false
        }
    }
}