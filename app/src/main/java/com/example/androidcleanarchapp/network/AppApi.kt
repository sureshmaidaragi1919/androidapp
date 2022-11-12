package com.example.androidcleanarchapp.network

import io.reactivex.rxjava3.core.Observer
import retrofit2.http.GET

interface AppApi {

    @GET("entries")
    fun fetchUser(): Observer<EntryResponse>
}