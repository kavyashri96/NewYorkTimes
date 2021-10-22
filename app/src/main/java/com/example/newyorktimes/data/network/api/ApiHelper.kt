package com.example.newyorktimes.data.network.api

import com.example.newyorktimes.data.models.NewsResult

class ApiHelper(val apiService: ApiService) {
    suspend fun getMostPopularNews(period: String): NewsResult = apiService.getMostPopularNews(period)
}