package com.core.network

import com.core.model.data.BlogDataItem
import com.core.model.data.BlogPost
import com.core.model.data.HomeData
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {
    @GET("posts")
    fun home(): Observable<HomeData>

    @GET("posts/{id}")
    fun detail(@Path("id") id: Int):Observable<BlogDataItem>

    @DELETE("posts/{id}")
    fun deleteBlog(@Path("id") id: Int):Observable<BlogDataItem>

    @POST("posts")
    fun createBlog(@Body blogPost: BlogPost):Observable<BlogDataItem>

    @PUT("posts/{id}")
    fun updateBlog(@Path("id") id: Int,@Body blogPost: BlogPost):Observable<BlogDataItem>
}