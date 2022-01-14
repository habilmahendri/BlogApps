package com.blogapps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blogapps.base.BaseViewModel
import com.blogapps.utils.NetworkState
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.BlogDataItem
import com.core.model.data.HomeData

class HomeViewModel  constructor(private val useCase: HomeUseCase) : BaseViewModel() {
    private val _home = MutableLiveData<HomeData>()
    val home: LiveData<HomeData>
        get() = _home

    private val _deleteBlog = MutableLiveData<BlogDataItem>()
    val deleteBlog: LiveData<BlogDataItem>
        get() = _deleteBlog

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState


    fun home() {
        _networkState.postValue(NetworkState.LOADING)
        useCase.home().subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _home.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }
    fun deleteBlog(id:Int) {
        _networkState.postValue(NetworkState.LOADING)
        useCase.delete(id).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _deleteBlog.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }
}