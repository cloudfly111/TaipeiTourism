package com.judy.demo.taipei.tourism.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.judy.demo.taipei.tourism.MainActivity
import com.judy.demo.taipei.tourism.R
import com.judy.demo.taipei.tourism.databinding.ItemAttractionBinding
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionNameAndImage
import com.judy.demo.taipei.tourism.repository.dataClass.AttractionsData
import com.judy.demo.taipei.tourism.utils.loadImageFromUrl

class AttractionsAdapter : ListAdapter<AttractionNameAndImage, AttractionsAdapter.ItemViewHolder>(NewDiffUtil()){

    private var updateType:UpdateType = UpdateType.ALL

    inner class ItemViewHolder(val binding: ItemAttractionBinding) :
        RecyclerView.ViewHolder(binding.root)  {
        fun bindContent(updateType: UpdateType,data:AttractionNameAndImage) {
            when(updateType){
                UpdateType.TITLE -> bindName(data.name)
                UpdateType.IMAGE -> bindImageFromUrl(data.image)
                else -> {
                    bindName(data.name)
                    bindImageFromUrl(data.image)
                }
            }
            if(updateType==UpdateType.ALL){
                bindImageFromUrl(data.image)
                bindName(data.name)
                bindIntro(data.address)
            }

        }
        fun bindImageFromUrl(url:String){
            loadImageFromUrl(context = binding.root.context,url=url, imageView = binding.AttractionImageView)
        }
        fun bindName(name:String){
            binding.NameTextView.text = name
        }
        fun bindIntro(introContent:String){
            binding.AddressView.text = introContent
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAttractionBinding.inflate(LayoutInflater.from(parent.context))
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemData = getItem(position)
        holder.bindContent(updateType,itemData)
        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("ID",itemData.id)
            it.findNavController().navigate(R.id.action_from_mainFragment_to_attractionsFragment,bundle)
        }
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val itemData = getItem(position)
        if(payloads.isNotEmpty()){
            val updateType = payloads[0] as UpdateType
            holder.bindContent(updateType,itemData)
        }else{
            super.onBindViewHolder(holder, position, payloads)
        }
    }
}