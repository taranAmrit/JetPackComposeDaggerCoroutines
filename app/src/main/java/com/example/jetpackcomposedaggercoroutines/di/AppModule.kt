package com.example.jetpackcomposedaggercoroutines.di

import com.example.jetpackcomposedaggercoroutines.data.AppConstants
import com.example.jetpackcomposedaggercoroutines.data.api.ApiService
import com.example.jetpackcomposedaggercoroutines.data.dataSource.NewsDataSource
import com.example.jetpackcomposedaggercoroutines.data.dataSource.NewsDataSourceImpl
import com.example.jetpackcomposedaggercoroutines.ui.repositories.NewsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

   @Provides
   @Singleton
    fun provoidesRetrofit():Retrofit{
        val httpLoggingInterceptor=HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }

        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit):ApiService{
       return retrofit.create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun providesNewsDataSource(apiService: ApiService):NewsDataSource{
        return NewsDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsDataSource: NewsDataSource):NewsRepository{
        return NewsRepository(newsDataSource)
    }
}