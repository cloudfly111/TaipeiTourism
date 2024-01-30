package com.judy.demo.taipei.tourism.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/** 由網址下載圖片 */
fun loadImageFromUrl(
   context: Context,
   url: String,
   height: Int = 300,
   width: Int = 500,
   imageView: ImageView
) {
   Glide.with(context).load(url).override(width, height).into(imageView)
}

