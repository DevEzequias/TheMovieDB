package com.devezequias.themoviedbkotlin.model.repository

import com.devezequias.themoviedbkotlin.model.entity.Details.DetailsModel
import com.devezequias.themoviedbkotlin.model.entity.Genres.GenreResponse
import com.devezequias.themoviedbkotlin.model.entity.NowPlaying.MovieResponse
import com.devezequias.themoviedbkotlin.model.entity.Similar.SimilarResponse
import com.devezequias.themoviedbkotlin.model.service.MovieApi
import com.devezequias.themoviedbkotlin.model.service.RetrofitFactory
import com.devezequias.themoviedbkotlin.model.service.Credentials


class MovieRepository {

    private val api: MovieApi = RetrofitFactory.makeRetrofit

    suspend fun getNowPlaying(language: String, page: Int): MovieResponse? {
        val response = api.getNowPlaying(Credentials.API_KEY, language, page)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getSimilar(movieId: Int, page: Int): SimilarResponse? {
        val response = api.getSimilar(movieId, Credentials.API_KEY, page)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getGenres(): GenreResponse? {
        val response = api.getGenres(Credentials.API_KEY, Credentials.LANGUAGE)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun getDetails(movieId: Int): DetailsModel? {
        val response = api.getDetails(movieId, Credentials.API_KEY, Credentials.LANGUAGE)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}