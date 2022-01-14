package com.core.network

import com.core.model.data.BlogDataItem
import com.core.model.data.HomeData
import io.reactivex.Observable
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun home(): Observable<HomeData>

    @GET("posts/{id}")
    fun detail(@Path("id") id: Int):Observable<BlogDataItem>

    @DELETE("posts/{id}")
    fun deleteBlog(@Path("id") id: Int):Observable<BlogDataItem>
}