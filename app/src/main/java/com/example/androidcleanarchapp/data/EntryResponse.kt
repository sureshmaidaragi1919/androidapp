package com.example.androidcleanarchapp.data

import com.google.gson.annotations.SerializedName

data class EntryResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("entries")
    val entriesList: List<Entry>?,
) {
    data class Entry(
        @SerializedName("API")
        val api: String?,
        @SerializedName("Description")
        val description: String?,
        @SerializedName("Auth")
        val auth: String?,
        @SerializedName("HTTPS")
        val https: String?,
        @SerializedName("Cors")
        val cors: String?,
        @SerializedName("Link")
        val link: String?,
        @SerializedName("Category")
        val category: String?,
    )
}