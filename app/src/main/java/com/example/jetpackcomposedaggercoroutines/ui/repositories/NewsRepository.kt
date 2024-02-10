package com.example.jetpackcomposedaggercoroutines.ui.repositories

import com.example.jetpackcomposedaggercoroutines.data.dataSource.NewsDataSource
import com.example.jetpackcomposedaggercoroutines.data.entity.NewsResponse
import com.example.jetpackcomposedaggercoroutines.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(val newsDataSource: NewsDataSource) {

    suspend fun getNewsHeadline(country:String): Flow<ResourceState<NewsResponse>> {

        return flow {
            emit(ResourceState.Loading())

            val response = newsDataSource.getNewsHeadline(country)

            if(response.isSuccessful && response.body()!=null){
                emit(ResourceState.Success(response.body()!!))
            }else{
                emit(ResourceState.Error("Error in fetching data"))
            }
        }.catch {e->
            emit(ResourceState.Error(e.localizedMessage?:"Some error in flow"))
        }

    }

}