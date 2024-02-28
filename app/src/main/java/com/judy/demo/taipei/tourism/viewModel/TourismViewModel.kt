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
import com.judy.demo.taipei.tourism.repository.languageType.LanguageType
import com.judy.demo.taipei.tourism.repository.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TourismViewModel : ViewModel() {
    val attractionsData = MutableLiveData<List<AttractionsData>>()
    val newsData = MutableLiveData<List<NewsData>>()
    val languageType = MutableLiveData<LanguageType>(LanguageType.ZH_TW)


    fun modifiyAttractionsData(): MutableList<AttractionNameAndImage> {
        var data: MutableList<AttractionNameAndImage> = mutableListOf()
        viewModelScope.launch {
            data = async { AttractionsRepo(attractionsData.value!!).modifiyAttractionsData() }.await()
        }
        return data.filter { it.image.isNotEmpty() }.toMutableList()
    }

    fun modifiyNewsData(): MutableList<NewsMessage> {
        var data: MutableList<NewsMessage> = mutableListOf()
        viewModelScope.launch {
            data = async { NewsRepo(newsData.value!!).modifiyNewsData() }.await()
        }
        return data
    }

    fun findAttractionsDataFromId(id:String):AttractionsData{
        var data:AttractionsData = AttractionsData()
        viewModelScope.launch {
            data = async { AttractionsRepo(attractionsData.value!!).findDataFromId(id) }.await()
        }
        return data
    }
}