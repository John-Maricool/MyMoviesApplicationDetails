package com.maricoolsapps.sportsapplication.ui.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maricoolsapps.sportsapplication.data.models.Credit
import com.maricoolsapps.sportsapplication.data.models.Image
import com.maricoolsapps.sportsapplication.data.models.Person
import com.maricoolsapps.sportsapplication.data.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel
@Inject constructor(val repository: MovieListRepository) : ViewModel() {

    val person = mutableStateOf(Person())
    val isLoading = mutableStateOf(true)
    val images: MutableState<List<Image>?> = mutableStateOf(null)
    val credits: MutableState<List<Credit>?> = mutableStateOf(null)

    var isConnected = false

    fun loadData(id: Long) {
        isConnected = repository.isConnected()
        if (repository.isConnected())
            getPersonDetails(id)
    }

    private fun getPersonDetails(id: Long) {
        viewModelScope.launch {
            person.value = repository.getPersonDetails(id)
            images.value = repository.getPersonPictures(id)
            credits.value = repository.getPersonFeaturedMovies(id)
            isLoading.value = false
        }
    }
}