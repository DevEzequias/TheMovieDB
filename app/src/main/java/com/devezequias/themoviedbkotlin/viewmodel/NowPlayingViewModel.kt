package com.devezequias.themoviedbkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devezequias.themoviedbkotlin.model.entity.NowPlaying.MovieModel
import com.devezequias.themoviedbkotlin.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NowPlayingViewModel : ViewModel() {

    private val repository = MovieRepository()
    val nowPlaying = MutableLiveData<List<MovieModel>>()

    fun getNowPlaying(language: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = repository.getNowPlaying(
                language, page)
            nowPlaying.postValue(movieResponse?.movies)
        }
    }
}