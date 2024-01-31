package com.judy.demo.taipei.tourism.repository.network

import com.judy.demo.taipei.tourism.repository.dataClass.APIStruct
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionsData
import com.judy.demo.taipei.tourism.repository.dataClass.NewsData
import retrofit2.Call
import retrofit2.http.*
/** API 串接*/
interface APIService {
    /** 最新消息 API*/
    @Headers("Accept: application/json")
    @GET("{lang}/Events/News/")
    fun getNewsData(
        @Path("lang") lang:String,
    ):Call<APIStruct<NewsData>>

    /** 景點 API*/
    @Headers("Accept: application/json")
    @GET("{lang}/Attractions/All/")
    fun getAttractionsData(
        @Path("lang") lang:String,
    ): Call<APIStruct<AttractionsData>>
}
