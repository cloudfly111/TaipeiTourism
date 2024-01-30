package com.judy.demo.taipei.tourism.repository.dataClass


/** 遊憩景點 API response*/
data class AttractionsData(
    val id:String,
    val name: String,
    val name_zh:String?="",
    val open_status:Int,
    val introduction: String,
    val open_time: String,
    val zipcode: String,
    val distric:String,
    val address:String,
    val tel:String,
    val fax:String,
    val email:String,
    val months:String,
    val nlat:Float,
    val elong:Float,
    val official_site:String,
    val facebook:String,
    val ticket:String,
    val remind:String,
    val staytime:String,
    val modified:String,
    val url:String,
    val category:List<Category>,
    val target: List<String>,
    val service:List<ServiceType>,
    val friendly:List<Any>,
    val images:List<ImageSrc>,
    val files:List<Any>,
    val links:List<Any>
)

data class Category(
    val id:String,
    val name:String
)
data class ServiceType(
    val id:String,
    val name:String
)
data class ImageSrc(
    val src:String,
    val subject:String,
    val ext:String
)


