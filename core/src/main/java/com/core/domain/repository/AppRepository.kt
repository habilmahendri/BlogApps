package com.core.domain.repository

import com.core.model.data.BlogDataItem
import com.core.model.data.BlogPost
import com.core.model.data.HomeData
import io.reactivex.Observable

interface AppRepository {
    fun home(): Observable<HomeData>
    fun detail(id:Int): Observable<BlogDataItem>
    fun deleteBlog(id:Int): Observable<BlogDataItem>
    fun createBlog(data:BlogPost): Observable<BlogDataItem>
    fun updateBlog(id:Int, data:BlogPost): Observable<BlogDataItem>
}