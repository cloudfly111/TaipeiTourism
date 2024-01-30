package com.judy.demo.taipei.tourism.adapter

import androidx.recyclerview.widget.DiffUtil
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionNameAndImage
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionsData

/** 資料更新類型 */
enum class UpdateType{
    /** 更新圖片 */
    IMAGE,
    /** 更新標題 */
    TITLE,
    /** 更新所有資料 */
    ALL,
}

class NewDiffUtil : DiffUtil.ItemCallback<AttractionNameAndImage>(){

    override fun areItemsTheSame(oldItem: AttractionNameAndImage, newItem: AttractionNameAndImage): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: AttractionNameAndImage, newItem: AttractionNameAndImage): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: AttractionNameAndImage, newItem: AttractionNameAndImage): Any? {
        var type:UpdateType = UpdateType.ALL
        if(!oldItem.image.equals(newItem.image) && !oldItem.name.equals(newItem.name)){
            type = UpdateType.ALL
        }else{
            if (!oldItem.image.equals(newItem.image)) {
                type = UpdateType.IMAGE
            }
            if (!oldItem.name.equals(newItem.name)) {
                type = UpdateType.TITLE
            }
        }
        return type

    }

}