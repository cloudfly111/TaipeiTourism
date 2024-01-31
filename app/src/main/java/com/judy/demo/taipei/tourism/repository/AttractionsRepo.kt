package com.judy.demo.taipei.tourism.repository

import com.judy.demo.taipei.tourism.repository.dataClass.AttractionNameAndImage
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionsData

class AttractionsRepo(private val dataSource: List<AttractionsData> ,) {
  var listData  = listOf<AttractionsData>()
  val simpleListData = mutableListOf<AttractionNameAndImage>()
    init {
        listData = dataSource
    }
    suspend fun modifiyAttractionsData(): MutableList<AttractionNameAndImage> {
        for (item in listData){
            simpleListData.add(item.toAttractionNameAndImage())
        }
        return simpleListData
    }

    suspend fun findDataFromId(id : String):AttractionsData{
        val targrtData = dataSource.find { data->data.id.equals(id) }
       return targrtData ?:AttractionsData()
    }
}