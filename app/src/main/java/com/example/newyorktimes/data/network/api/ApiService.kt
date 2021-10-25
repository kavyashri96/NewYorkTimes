package com.example.newyorktimes.data.network.api

import com.example.newyorktimes.data.models.NewsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("mostpopular/v2/viewed/{period}.json")
    suspend fun getMostPopularNews(
        @Path("period") period: String = "7"
    ): NewsResult

    companion object {
        const val API_KEY = "cxvKKuxGk8M2ARlt7cJtWgkk3fTVRUxm"
    }
}