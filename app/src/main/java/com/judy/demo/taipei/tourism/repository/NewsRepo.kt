package com.judy.demo.taipei.tourism.repository

import com.judy.demo.taipei.tourism.repository.dataClass.NewsData
import com.judy.demo.taipei.tourism.repository.dataClass.NewsMessage

class NewsRepo(private val dataSource: List<NewsData>) {
    var listData = listOf<NewsData>()
    val simpleListData = mutableListOf<NewsMessage>()

    init {
        listData = dataSource
    }

    suspend fun modifiyNewsData(): MutableList<NewsMessage> {
        for (item in listData) {
            simpleListData.add(item.toNewsMessage())
        }
        return simpleListData
    }
}