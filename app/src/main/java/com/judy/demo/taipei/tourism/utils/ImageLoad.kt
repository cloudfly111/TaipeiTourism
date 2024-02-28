package com.judy.demo.taipei.tourism.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.judy.demo.taipei.tourism.R

/** 由網址下載圖片 */
fun loadImageFromUrl(
   context: Context,
   url: String,
   height: Int = 300,
   width: Int = 500,
   imageView: ImageView
) {

   if(url.isNotBlank()){
      Log.i("loadImageFromUrl","[$url]")
      /** 有 url 則由網址載入圖片 */
      Glide.with(context).load(url).override(width, height).into(imageView)
   }else{
      /** 載入預設圖片*/
      imageView.setImageResource(R.drawable.image)
   }

}

