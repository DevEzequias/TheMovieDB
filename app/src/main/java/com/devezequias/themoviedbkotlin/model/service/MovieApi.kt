package com.devezequias.themoviedbkotlin.model.service

import com.devezequias.themoviedbkotlin.model.entity.Details.DetailsModel
import com.devezequias.themoviedbkotlin.model.entity.Genres.GenreResponse
import com.devezequias.themoviedbkotlin.model.entity.NowPlaying.MovieResponse
import com.devezequias.themoviedbkotlin.model.entity.Similar.SimilarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") language: String
    ): Response<DetailsModel>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Response<SimilarResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String,
        @Query("language") language: String
    ): Response<GenreResponse>
}