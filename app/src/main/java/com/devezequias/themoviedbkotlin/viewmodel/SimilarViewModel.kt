package com.devezequias.themoviedbkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devezequias.themoviedbkotlin.model.entity.Similar.SimilarModel
import com.devezequias.themoviedbkotlin.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SimilarViewModel : ViewModel() {

    private val repository = MovieRepository()
    val similar = MutableLiveData<List<SimilarModel>>()
    val similarError = MutableLiveData<Unit>()

    fun getSimilar(movieId: Int, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val similarResponse = repository.getSimilar(
                movieId, page)
            if (similarResponse == null) {
                similarError.postValue(Unit)
            } else {
                similar.postValue(similarResponse.movies)
            }
        }
    }
}