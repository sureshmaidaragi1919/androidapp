package com.example.androidcleanarchapp.presentation.itemsview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidcleanarchapp.R
import com.example.androidcleanarchapp.data.Entry
import com.example.androidcleanarchapp.databinding.LayoutListItemBinding

class ItemsAdapter() : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    var itemsList = listOf<Entry>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(itemsList[position])
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    class ItemViewHolder constructor(
        private val itemBinding: LayoutListItemBinding, private val parent: ViewGroup
    ) : ViewHolder(itemBinding.root) {

        fun bindView(item: Entry) {
            itemBinding.itemTitle.text = parent.context.resources.getString(R.string.api, item.api)
            itemBinding.itemDesc.text =
                parent.context.resources.getString(R.string.desc, item.description)
        }

    }
}