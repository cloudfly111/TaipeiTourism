package com.judy.demo.taipei.tourism.repository

import com.judy.demo.taipei.tourism.repository.dataClass.AttractionNameAndImage
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionsData

class AttractionsRepo(private val dataSource: List<AttractionsData> ,) {
  var listData  = listOf<AttractionsData>()
  val simpleListData = mutableListOf<AttractionNameAndImage>()
    init {
        listData = dataSource
    }
    fun modifiyAttractionsData(): MutableList<AttractionNameAndImage> {
        for (item in listData){
            simpleListData.add(item.toAttractionNameAndImage())
        }
        return simpleListData
    }
}