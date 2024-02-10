package com.example.jetpackcomposedaggercoroutines.data.dataSource

import com.example.jetpackcomposedaggercoroutines.data.api.ApiService
import com.example.jetpackcomposedaggercoroutines.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class  NewsDataSourceImpl @Inject constructor(private val apiService: ApiService):NewsDataSource {

    override suspend fun getNewsHeadline(country: String): Response<NewsResponse> {

        return apiService.getNewsHeadline(country)
    }
}