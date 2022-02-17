package com.devezequias.themoviedbkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devezequias.themoviedbkotlin.model.entity.Genres.GenreModel
import com.devezequias.themoviedbkotlin.model.entity.Similar.SimilarModel
import com.devezequias.themoviedbkotlin.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GenresViewModel : ViewModel() {

    private val repository = MovieRepository()
    val genres = MutableLiveData<List<GenreModel>>()
    val genresError = MutableLiveData<Unit>()

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {
            val genresResponse = repository.getGenres()
            if (genresResponse == null) {
                genresError.postValue(Unit)
            } else {
                genres.postValue(genresResponse.genres)
            }
        }
    }
}