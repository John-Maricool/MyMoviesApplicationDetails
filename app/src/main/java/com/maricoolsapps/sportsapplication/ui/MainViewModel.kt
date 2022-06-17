package com.maricoolsapps.sportsapplication.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.models.MoviesListItem
import com.maricoolsapps.sportsapplication.data.models.TvListItem
import com.maricoolsapps.sportsapplication.data.repository.MovieListRepository
import com.maricoolsapps.sportsapplication.utils.Constants.ALL_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.IN_THEATRE_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.POPULAR_MOVIES
import com.maricoolsapps.sportsapplication.utils.Constants.UPCOMING_MOVIES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(val repository: MovieListRepository) : ViewModel() {

    var popularMovieList: Flow<PagingData<MovieListItemModel>> = flowOf()
    var inTheatreMovieList: Flow<PagingData<MovieListItemModel>> = flowOf()
    var upcomingMovieList: Flow<PagingData<MovieListItemModel>> = flowOf()
    var allMovieList: Flow<PagingData<MovieListItemModel>> = flowOf()
    var tvShowList: Flow<PagingData<MovieListItemModel>> = flowOf()

    val isLoading = mutableStateOf(false)

    fun getPopularMovies() {
        isLoading.value = true
        try {
            viewModelScope.launch {
                val result = repository.getMovieList(POPULAR_MOVIES)
                    .cachedIn(viewModelScope)
                popularMovieList = result
                isLoading.value = false
            }
        } catch (e: Exception) {
            isLoading.value = false
        }
    }

    fun getInTheatreMovies() {
        isLoading.value = true
        try {
            viewModelScope.launch {
                val result = repository.getMovieList(IN_THEATRE_MOVIES)
                    .cachedIn(viewModelScope)
                inTheatreMovieList = result
                isLoading.value = false
            }
        } catch (e: Exception) {
            isLoading.value = false
            Log.d("errors", "Error getting data")
        }
    }

    fun getUpcomingMovies() {
        isLoading.value = true
        try {
            viewModelScope.launch {
                val result = repository.getMovieList(UPCOMING_MOVIES)
                    .cachedIn(viewModelScope)
                upcomingMovieList = result
                isLoading.value = false
            }
        } catch (e: Exception) {
            isLoading.value = false
            Log.d("errors", "Error getting data")
        }
    }

    fun getAllMovies() {
        isLoading.value = true
        try {
            viewModelScope.launch {
                val result = repository.getMovieList(ALL_MOVIES)
                    .cachedIn(viewModelScope)
                allMovieList = result
                isLoading.value = false
            }
        } catch (e: Exception) {
            isLoading.value = false
            Log.d("errors", "Error getting data")
        }
    }

    fun getTvShows() {
        isLoading.value = true
        try {
            viewModelScope.launch {
                val result = repository.getTvList()
                    .cachedIn(viewModelScope)
                tvShowList = result
                isLoading.value = false
            }
        } catch (e: Exception) {
            isLoading.value = false
            Log.d("errors", "Error getting data")
        }
    }

}