package com.core.domain.usecase

import com.core.model.data.BlogDataItem
import io.reactivex.Observable

interface DetailBlogUseCase {
    fun detail(id:Int): Observable<BlogDataItem>
}