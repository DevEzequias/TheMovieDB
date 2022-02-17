package com.devezequias.themoviedbkotlin.model.entity.Genres

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<GenreModel>
)