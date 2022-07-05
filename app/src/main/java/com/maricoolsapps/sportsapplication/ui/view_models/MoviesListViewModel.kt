package com.maricoolsapps.sportsapplication.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.repository.MovieListRepository
import com.maricoolsapps.sportsapplication.utils.Constants
import com.maricoolsapps.sportsapplication.utils.MovieTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel
@Inject constructor(val repository: MovieListRepository) : ViewModel() {

    var allMovieList: Flow<PagingData<MovieListItemModel>> = flowOf()

    var isConnected = false

    fun loadData() {
        isConnected = repository.isConnected()
        if (repository.isConnected())
            getAllMovies()
    }

    private fun getAllMovies() {
        try {
            viewModelScope.launch {
                val result = repository.getAllMovieAndTvList(Constants.ALL_MOVIES)
                    .cachedIn(viewModelScope)
                allMovieList = result
            }
        } catch (e: Exception) {
            Log.d("errors", "Error getting data")
        }
    }
}