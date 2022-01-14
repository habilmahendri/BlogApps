package com.core.domain.interactor

import com.core.domain.repository.AppRepository
import com.core.domain.usecase.DetailBlogUseCase
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.BlogDataItem
import com.core.model.data.HomeData
import io.reactivex.Observable

class DetailBlogInteractor constructor(private val appRepository: AppRepository): DetailBlogUseCase {
    override fun detail(id:Int): Observable<BlogDataItem> = appRepository.detail(id)
}