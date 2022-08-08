package com.assignment.newsapplication.adapter.source

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.common.entity.source.Source
import com.assignment.common.extention.Constants
import com.assignment.common.extention.Constants.loadImageFromUrl
import com.assignment.newsapplication.databinding.LayoutSourceItemBinding

class SourceAdapter(val selectSource: (Source) -> Unit) :
    RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    val listData = AsyncListDiffer<Source>(this, differ)

    inner class SourceViewHolder(val binding: LayoutSourceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source) {
            binding.root.setOnClickListener {
                selectSource(source)
            }
            binding.sourceImage.loadImageFromUrl(Constants.getImageFromUrl(source))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder =
        SourceViewHolder(
            LayoutSourceItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(listData.currentList[position])
        holder.binding.data = listData.currentList[position]
    }

    override fun getItemCount(): Int {
        return listData.currentList.size
    }

    fun sendData(source: List<Source>) {
        listData.submitList(source)
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<Source>() {
            override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem == newItem
            }

        }
    }
}