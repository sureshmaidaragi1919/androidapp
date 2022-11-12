package com.example.androidcleanarchapp.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppApi {

    @POST("add")
    fun addEntries(@Path("entries") entriesList: List<EntryResponse.Entry>): Observable<Boolean>

    @GET("entries")
    fun fetchUser(): Observable<EntryResponse>
}