package com.devezequias.themoviedbkotlin.model.entity.Similar

import com.google.gson.annotations.SerializedName

data class SimilarResponse (
    @SerializedName("results")
    val movies: List<SimilarModel>
)