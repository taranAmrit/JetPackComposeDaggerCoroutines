package com.example.jetpackcomposedaggercoroutines.data.api

import com.example.jetpackcomposedaggercoroutines.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
   suspend fun getNewsHeadline(
        @Query("q") country:String,
        @Query("apiKey") apiKey:String= "ae242841897a49f5abcfbf8c8afa59d1"
    ): Response<NewsResponse>

  //  https://newsapi.org/v2/everything?q=bitcoin&apiKey=ae242841897a49f5abcfbf8c8afa59d1
   // https://newsapi.org/v2/everything?q=tesla&from=2023-12-31&sortBy=publishedAt&apiKey=ae242841897a49f5abcfbf8c8afa59d1
}