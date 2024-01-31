package com.judy.demo.taipei.tourism.repository.dataClass


/** 遊憩景點 API response*/
data class AttractionsData(
    val id: String = "",
    val name: String = "",
    val name_zh: String? = "",
    val open_status: Int = 1,
    val introduction: String = "",
    val open_time: String = "",
    val zipcode: String = "",
    val distric: String = "",
    val address: String = "",
    val tel: String = "",
    val fax: String = "",
    val email: String = "",
    val months: String = "",
    val nlat: Float = 0.0F,
    val elong: Float = 0.0F,
    val official_site: String = "",
    val facebook: String = "",
    val ticket: String = "",
    val remind: String = "",
    val staytime: String = "",
    val modified: String = "",
    val url: String = "",
    val category: List<Category> = emptyList(),
    val target: List<Any> = emptyList(),
    val service: List<ServiceType> = emptyList(),
    val friendly: List<Any> = emptyList(),
    val images: List<ImageSrc> = emptyList(),
    val files: List<Any> = emptyList(),
    val links: List<Any> = emptyList()
) {
    fun toAttractionNameAndImage(): AttractionNameAndImage {
        val imageSrc = if (this.images.isNotEmpty()) images[0].src else ""
        return AttractionNameAndImage(this.id,this.name, imageSrc)
    }
}

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

data class AttractionNameAndImage(
    val id:String,
    val name:String,
    val image:String,
)

