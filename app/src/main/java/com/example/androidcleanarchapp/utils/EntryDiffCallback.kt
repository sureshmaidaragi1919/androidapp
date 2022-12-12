package com.example.androidcleanarchapp.utils

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.androidcleanarchapp.data.Entry


class EntryDiffCallback(private val oldList: List<Entry>, private val newList: List<Entry>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].api != newList[newItemPosition].api -> {
                false
            }
            oldList[oldItemPosition].description != newList[newItemPosition].description -> {
                false
            }
            oldList[oldItemPosition].link != newList[newItemPosition].link -> {
                false
            }
            else -> {
                true
            }

        }
    }

}