package com.devezequias.themoviedbkotlin.model.entity.Similar

import com.google.gson.annotations.SerializedName

data class SimilarModel(
    @SerializedName("id")
    val movie_id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("genre_ids")
    val genres: List<Int>,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("poster_path")
    val poster_path: String
)