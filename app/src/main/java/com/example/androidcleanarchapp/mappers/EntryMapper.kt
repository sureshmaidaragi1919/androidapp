package com.example.androidcleanarchapp.mappers

import com.example.androidcleanarchapp.network.EntryModel
import com.example.androidcleanarchapp.network.EntryResponse

object EntryMapper {

    fun mapEntryResponseToEntryModel(response: EntryResponse) =
        EntryModel(
            count = response.count?:0,
            entriesList = response.entriesList?.map {
                EntryModel.Entry(
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