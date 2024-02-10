package com.example.jetpackcomposedaggercoroutines.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposedaggercoroutines.ui.components.EmptyRowComponent
import com.example.jetpackcomposedaggercoroutines.ui.components.Loader
import com.example.jetpackcomposedaggercoroutines.ui.components.NewsRowComponent
import com.example.jetpackcomposedaggercoroutines.ui.viewModels.NewsViewModel
import com.example.jetpackcomposedaggercoroutines.utilities.ResourceState


const val TAG = "HomeScreenTag"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(newsViewModel: NewsViewModel = hiltViewModel()) {


    val newsResponse by newsViewModel.news.collectAsState()

    val pagerState= rememberPagerState(initialPage = 0,
        initialPageOffsetFraction = 0f
    )

    VerticalPager(state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
        pageCount = 100,
        pageSpacing = 8.dp) {page:Int ->

        when (newsResponse) {
            is ResourceState.Loading -> {
                Log.d(TAG, "loading")

                Loader()
            }

            is ResourceState.Success -> {

                val response = (newsResponse as ResourceState.Success).data
                Log.d(TAG, "Inside success ${response.status}")
                if(response.articles.isNotEmpty()){
                    NewsRowComponent(page,response.articles.get(page))
                }else{
                    EmptyRowComponent()
                }
            }

            is ResourceState.Error -> {
                val error = (newsResponse as ResourceState.Error).error
                Log.d(TAG, "Inside error $error")
            }
        }
    }

}




@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

