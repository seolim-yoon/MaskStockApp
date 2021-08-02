package com.example.maskstockapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.maskstockapp.data.model.MaskStoreModel
import com.example.maskstockapp.databinding.ItemStoreBinding

class MaskStoreListAdapter() :
    ListAdapter<MaskStoreModel, MaskStoreListAdapter.MaskStoreViewHolder>(diffUtil) {
    inner class MaskStoreViewHolder(private val binding: ItemStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(maskStoreModel: MaskStoreModel) {
            binding.store = maskStoreModel

            itemView.setOnClickListener {
//                historyItemClick(MaskStoreModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskStoreViewHolder =
        MaskStoreViewHolder(
            ItemStoreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MaskStoreViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MaskStoreModel>() {
            override fun areContentsTheSame(oldItem: MaskStoreModel, newItem: MaskStoreModel) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: MaskStoreModel, newItem: MaskStoreModel) =
                oldItem.code == newItem.code
        }
    }
}