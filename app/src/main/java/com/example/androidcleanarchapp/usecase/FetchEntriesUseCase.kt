package com.example.androidcleanarchapp.usecase

import com.example.androidcleanarchapp.data.EntryModel
import com.example.androidcleanarchapp.mappers.EntryMapper
import com.example.androidcleanarchapp.repository.EntriesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FetchEntriesUseCase @Inject constructor(private val repository: EntriesRepository) {

    fun execute(): Observable<EntryModel> {
        return repository.fetchEntries()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { EntryMapper.mapEntryResponseToEntryModel(it) }
    }
}