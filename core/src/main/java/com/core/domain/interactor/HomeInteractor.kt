package com.core.domain.interactor

import com.core.domain.repository.AppRepository
import com.core.domain.usecase.HomeUseCase
import com.core.model.data.BlogDataItem
import com.core.model.data.HomeData
import io.reactivex.Observable

class HomeInteractor constructor(private val appRepository: AppRepository): HomeUseCase {
    override fun home(): Observable<HomeData> = appRepository.home()
    override fun delete(id: Int): Observable<BlogDataItem> = appRepository.deleteBlog(id)
}