package com.core.domain.usecase

import com.core.model.data.BlogDataItem
import com.core.model.data.HomeData
import io.reactivex.Observable

interface HomeUseCase {
    fun home(): Observable<HomeData>
    fun delete(id:Int): Observable<BlogDataItem>
}