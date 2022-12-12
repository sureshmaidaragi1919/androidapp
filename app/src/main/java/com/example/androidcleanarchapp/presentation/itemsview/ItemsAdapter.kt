package com.example.androidcleanarchapp.presentation.itemsview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidcleanarchapp.R
import com.example.androidcleanarchapp.data.Entry
import com.example.androidcleanarchapp.databinding.LayoutListItemBinding
import com.example.androidcleanarchapp.utils.EntryDiffCallback
import kotlinx.coroutines.NonDisposableHandle.parent

class ItemsAdapter() : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {


    private var oldItemsList: List<Entry> = emptyList()

    fun submitList(newList: List<Entry>) {
        val diffUtil = EntryDiffCallback(oldItemsList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldItemsList = newList
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(oldItemsList[position])
    }

    override fun getItemCount(): Int {
        return oldItemsList.size
    }

    class ItemViewHolder constructor(
        private val itemBinding: LayoutListItemBinding
    ) : ViewHolder(itemBinding.root) {

        fun bindView(item: Entry) {
            itemBinding.itemTitle.text =
                itemBinding.root.resources.getString(R.string.api, item.api)
            itemBinding.itemDesc.text =
                itemBinding.root.resources.getString(R.string.desc, item.description)
        }

    }
}