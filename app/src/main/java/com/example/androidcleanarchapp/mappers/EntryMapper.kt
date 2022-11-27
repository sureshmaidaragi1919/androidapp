package com.example.androidcleanarchapp.mappers

import com.example.androidcleanarchapp.data.Entry
import com.example.androidcleanarchapp.data.EntryModel
import com.example.androidcleanarchapp.data.EntryResponse

object EntryMapper {

    fun mapEntryResponseToEntryModel(response: EntryResponse) =
        EntryModel(
            count = response.count?:0,
            entriesList = response.entriesList?.map {
              Entry(
                    api = it.api.orEmpty(),
                    description = it.description.orEmpty(),
                    auth = it.auth.orEmpty(),
                    https = it.https.orEmpty(),
                    cors = it.cors.orEmpty(),
                    link = it.link.orEmpty(),
                    category = it.category.orEmpty())
            }.orEmpty()
        )

}