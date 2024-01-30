package com.judy.demo.taipei.tourism.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judy.demo.taipei.tourism.repository.AttractionsRepo
import com.judy.demo.taipei.tourism.repository.NewsRepo
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionNameAndImage
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionsData
import com.judy.demo.taipei.tourism.repository.dataClass.NewsData
import com.judy.demo.taipei.tourism.repository.dataClass.NewsMessage
import com.judy.demo.taipei.tourism.repository.network.RetrofitInstance
import kotlinx.coroutines.launch

class TourismViewModel: ViewModel() {
    val attractionsData = MutableLiveData<List<AttractionsData>>()
    val newsData = MutableLiveData<List<NewsData>>()


    fun modifiyAttractionsData():MutableList<AttractionNameAndImage>{
        return AttractionsRepo(attractionsData.value!!).modifiyAttractionsData()
    }

    fun modifiyNewsData():MutableList<NewsMessage>{
        return NewsRepo(newsData.value!!).modifiyNewsData()
    }
}