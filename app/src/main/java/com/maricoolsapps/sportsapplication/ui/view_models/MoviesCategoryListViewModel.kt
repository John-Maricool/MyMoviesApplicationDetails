package com.maricoolsapps.sportsapplication.ui.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maricoolsapps.sportsapplication.data.models.MovieListItemModel
import com.maricoolsapps.sportsapplication.data.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesCategoryListViewModel
@Inject constructor(val repository: MovieListRepository) : ViewModel() {

    var movieList: Flow<PagingData<MovieListItemModel>> = flowOf()
    val isLoading = mutableStateOf(true)

    var isConnected = false

    fun loadData(type: String) {
        isConnected = repository.isConnected()
        if (repository.isConnected())
            getAllMovies(type)
    }

    private fun getAllMovies(type: String) {
        try {
            viewModelScope.launch {
                val result = repository.getMovieList(type)
                    .cachedIn(viewModelScope)
                movieList = result
                isLoading.value = false
            }
        } catch (e: Exception) {
            Log.d("errors", "Error getting data")
        }
    }
}