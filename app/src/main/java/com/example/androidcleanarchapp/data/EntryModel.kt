package com.example.androidcleanarchapp.data

data class EntryModel(
    var count: Int,
    val entriesList: List<Entry>,
)

data class Entry(
    val api: String,
    val description: String,
    val auth: String,
    val https: String,
    val cors: String,
    val link: String,
    val category: String,
)
