package com.example.jetpackcomposedaggercoroutines.data.dataSource

import com.example.jetpackcomposedaggercoroutines.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {
   suspend fun getNewsHeadline(country:String): Response<NewsResponse>
}