package com.devezequias.themoviedbkotlin.model.entity.Details

import com.google.gson.annotations.SerializedName

data class DetailsModel(
    @SerializedName("id")
    val movie_id: Int,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("poster_path")
    val poster_path: String = "",
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_count")
    val vote_count: Int
)