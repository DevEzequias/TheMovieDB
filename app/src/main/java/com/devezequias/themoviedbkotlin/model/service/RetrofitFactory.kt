package com.devezequias.themoviedbkotlin.model.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val makeRetrofit: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}