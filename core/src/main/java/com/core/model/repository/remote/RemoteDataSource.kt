package com.core.model.repository.remote

import com.core.network.ApiInterface

class RemoteDataSource constructor(private val apiService: ApiInterface)  {
    fun home() = apiService.home()
    fun deleteBlog(id:Int) = apiService.deleteBlog(id)
    fun detail(id:Int) = apiService.detail(id)
}