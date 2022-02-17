package com.devezequias.themoviedbkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devezequias.themoviedbkotlin.model.entity.Details.DetailsModel

import com.devezequias.themoviedbkotlin.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository = MovieRepository()
    val details = MutableLiveData<DetailsModel?>()
    val detailsError = MutableLiveData<Unit>()

    fun getDetails(movieId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val detailsResponse = repository.getDetails(movieId)
            if (detailsResponse == null) {
                detailsError.postValue(Unit)
            } else {
                details.postValue(detailsResponse)
            }
        }
    }
}