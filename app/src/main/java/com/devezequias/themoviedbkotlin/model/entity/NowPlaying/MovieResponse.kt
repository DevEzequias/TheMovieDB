package com.devezequias.themoviedbkotlin.model.entity.NowPlaying

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movies: List<MovieModel>
)
