package com.judy.demo.taipei.tourism.repository.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** 建立 Retrofit 實體*/
class RetrofitInstance {
    companion object{
        private val BASE_URL:String ="https://www.travel.taipei/open-api/"
        private val retrofit= Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
        val service = retrofit.create(APIService::class.java)
    }
}