package com.judy.demo.taipei.tourism.repository.dataClass

/** 最新消息 API response */
data class NewsData(
    val id: Int,
    val title: String,
    val description:String,
    val begin: String?,
    val end: String?,
    val posted: String,
    val modified: String,
    val url: String,
    val files: List<Any?>,
    val links: List<NewsLinks?>,
)

data class NewsLinks(
    val src: String,
    val subject: String,
)
