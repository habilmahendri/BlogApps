package com.core.domain.interactor

import com.core.domain.repository.AppRepository
import com.core.domain.usecase.CreateUpdateUseCase
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.BlogDataItem
import com.core.model.data.BlogPost
import com.core.model.data.HomeData
import io.reactivex.Observable

class CreateUpdateInteractor constructor(private val appRepository: AppRepository): CreateUpdateUseCase {
    override fun create(data: BlogPost): Observable<BlogDataItem> = appRepository.createBlog(data)
    override fun update(id:Int,data: BlogPost): Observable<BlogDataItem> = appRepository.updateBlog(id,data)
}