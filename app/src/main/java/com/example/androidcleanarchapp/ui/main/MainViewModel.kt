package com.example.androidcleanarchapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidcleanarchapp.network.EntryModel
import com.example.androidcleanarchapp.usecase.FetchEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: FetchEntriesUseCase) : ViewModel() {

    private val _data: MutableLiveData<State> = MutableLiveData()
    val data: LiveData<State> get() = _data

    private val compositeDisposable = CompositeDisposable()
    fun fetchEntry() {

        compositeDisposable.add(
            Observable.just(_data.postValue(State.Loading))
                .flatMap { useCase.execute() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _data.postValue(State.Success(it))
                }, {
                    _data.postValue(State.Failed(it))
                })
        )

    }

    sealed class State {
        object Loading : State()
        data class Success(val model: EntryModel) : State()
        data class Failed(val throwable: Throwable) : State()
    }

}