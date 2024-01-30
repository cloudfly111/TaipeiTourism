package com.judy.demo.taipei.tourism.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.judy.demo.taipei.tourism.databinding.ItemNewsBinding
import com.judy.demo.taipei.tourism.repository.dataClass.NewsMessage

class NewsAdapter : ListAdapter<NewsMessage, NewsAdapter.ItemViewHolder>(NewsAdapter.NewsDiffUtil) {


    inner class ItemViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(data: NewsMessage) {
            binding.apply {
                TitleTextView.text = data.title
                ContentTextView.text = data.description
                PostTimeTextView.text = data.posted
            }
        }

    }

    companion object NewsDiffUtil : DiffUtil.ItemCallback<NewsMessage>() {

        override fun areItemsTheSame(
            oldItem: NewsMessage,
            newItem: NewsMessage
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: NewsMessage,
            newItem: NewsMessage
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context))
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = getItem(position)
        holder.bindView(data)
    }

}