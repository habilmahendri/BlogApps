package com.core.domain.usecase

import com.core.model.data.BlogDataItem
import com.core.model.data.BlogPost
import io.reactivex.Observable

interface CreateUpdateUseCase {
    fun create(data:BlogPost): Observable<BlogDataItem>
    fun update(id:Int,data:BlogPost): Observable<BlogDataItem>

}