package com.example.newyorktimes.data.network.repository

import com.example.newyorktimes.data.network.api.ApiHelper

class MostPopularNewsRepository(val apiHelper: ApiHelper) {
    suspend fun getMostPopularNews(period: String) = apiHelper.getMostPopularNews(period)
}