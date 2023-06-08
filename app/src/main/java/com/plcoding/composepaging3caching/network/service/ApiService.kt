package com.plcoding.composepaging3caching.network.service

import com.plcoding.composepaging3caching.network.model.Beer
import retrofit2.http.GET
import retrofit2.http.Query


// https://api.punkapi.com/v2/beers?page=1&per_page=1


interface ApiService{
    @GET("beers")
    suspend fun getBeers(
        @Query("page" ) page:Int,
        @Query("per_page") per_page:Int
    ):List<Beer>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}