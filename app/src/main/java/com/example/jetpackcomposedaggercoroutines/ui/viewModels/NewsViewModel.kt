package com.example.jetpackcomposedaggercoroutines.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposedaggercoroutines.data.AppConstants
import com.example.jetpackcomposedaggercoroutines.data.entity.NewsResponse
import com.example.jetpackcomposedaggercoroutines.ui.repositories.NewsRepository
import com.example.jetpackcomposedaggercoroutines.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(val newsRepository: NewsRepository):ViewModel() {


   private val _news:MutableStateFlow<ResourceState<NewsResponse>> =MutableStateFlow(ResourceState.Loading())
    val news:StateFlow<ResourceState<NewsResponse>> = _news

     fun getNews(country:String){
         viewModelScope.launch(Dispatchers.IO) {
           newsRepository.getNewsHeadline(country).collectLatest { newsResponse->
               _news.value = newsResponse

           }


         }

    }

    init {
        getNews(AppConstants.COUNTRY)
    }


    companion object{
        const val TAG="NewsViewModel"
    }
}