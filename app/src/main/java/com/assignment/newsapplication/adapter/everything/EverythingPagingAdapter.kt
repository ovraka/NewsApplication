package com.assignment.newsapplication.adapter.everything

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.common.entity.everything.Article
import com.assignment.common.extention.Constants.loadImageFromUrl
import com.assignment.newsapplication.databinding.LayoutEverythingItemBinding

class EverythingPagingAdapter (val selectEverything: (String) -> Unit) :
    PagingDataAdapter<Article, EverythingPagingAdapter.EverythingPagingViewHolder>(differ) {
    inner class EverythingPagingViewHolder(
        val binding: LayoutEverythingItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            try {
                article?.urlToImage?.let { binding.image.loadImageFromUrl(it) }
            }catch (e:Exception){
                Log.e("Error",e.message?.let { it }?: "Error Load Image")
            }
            binding.readMore.setOnClickListener {
                article?.url?.let { it1 -> selectEverything(it1) }
            }
        }
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: EverythingPagingViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(getItem(position))
        holder.binding.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EverythingPagingViewHolder {
        return EverythingPagingViewHolder(
            LayoutEverythingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}