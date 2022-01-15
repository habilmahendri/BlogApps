package com.blogapps.ui.createupdate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blogapps.base.BaseViewModel
import com.blogapps.utils.NetworkState
import com.core.domain.usecase.CreateUpdateUseCase
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.BlogDataItem
import com.core.model.data.BlogPost
import com.core.model.data.HomeData

class CreateUpdateViewModel  constructor(private val useCase: CreateUpdateUseCase) : BaseViewModel() {
    private val _create = MutableLiveData<BlogDataItem>()
    val create: LiveData<BlogDataItem>
        get() = _create

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState


    fun create(data:BlogPost) {
        _networkState.postValue(NetworkState.LOADING)
        useCase.create(data).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _create.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }

    fun update(id:Int,data:BlogPost) {
        _networkState.postValue(NetworkState.LOADING)
        useCase.update(id,data).subscribe({
            _networkState.postValue(NetworkState.LOADED)
            _create.postValue(it)
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }

}