package com.blogapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blogapps.base.BaseViewModel
import com.blogapps.utils.NetworkState
import com.core.domain.usecase.DetailBlogUseCase
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.BlogDataItem
import com.core.model.data.HomeData

class DetailViewModel  constructor(private val useCase: DetailBlogUseCase) : BaseViewModel() {
    private val _detail = MutableLiveData<BlogDataItem>()
    val detail: LiveData<BlogDataItem>
        get() = _detail

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    fun detail(id:Int) {
        _networkState.postValue(NetworkState.LOADING)
        useCase.detail(id).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _detail.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }
}