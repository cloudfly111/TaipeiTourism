package com.judy.demo.taipei.tourism.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.judy.demo.taipei.tourism.databinding.ItemImageBinding
import com.judy.demo.taipei.tourism.utils.loadImageFromUrl

class ViewPagerAdapter(private val imageUrlList: List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bindView(data:String){
                loadImageFromUrl(context = binding.root.context,url=data, width = 1000, height = 700, imageView = binding.ImageView)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val data = imageUrlList[position]
        holder.bindView(data)
    }

    override fun getItemCount(): Int = imageUrlList.size
}