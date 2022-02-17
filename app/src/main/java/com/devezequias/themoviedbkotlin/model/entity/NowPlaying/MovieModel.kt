package com.devezequias.themoviedbkotlin.model.entity.NowPlaying
import com.google.gson.annotations.SerializedName

data class MovieModel(
        @SerializedName("id")
        val movie_id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("poster_path")
        val poster_path: String
)