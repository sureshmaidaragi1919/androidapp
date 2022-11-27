package com.example.androidcleanarchapp.repository

import com.example.androidcleanarchapp.network.AppApi
import com.example.androidcleanarchapp.data.EntryResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class EntriesRepository @Inject constructor(private val api: AppApi) {

    fun addEntries(list: List<EntryResponse.Entry>): Observable<Boolean> {
        return api.addEntries(list)
    }

    fun fetchEntries(): Observable<EntryResponse> {
        return api.fetchUser()
    }
}