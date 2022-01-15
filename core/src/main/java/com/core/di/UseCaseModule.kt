package com.core.di

import com.core.domain.interactor.CreateUpdateInteractor
import com.core.domain.interactor.DetailBlogInteractor
import com.core.domain.interactor.HomeInteractor
import com.core.domain.usecase.CreateUpdateUseCase
import com.core.domain.usecase.DetailBlogUseCase
import com.core.domain.usecase.HomeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<HomeUseCase> {
        HomeInteractor(get())
    }
    factory<DetailBlogUseCase> {
        DetailBlogInteractor(get())
    }

    factory<CreateUpdateUseCase> {
        CreateUpdateInteractor(get())
    }

}