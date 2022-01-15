package com.blogapps.di

import com.blogapps.ui.createupdate.CreateUpdateViewModel
import com.blogapps.ui.detail.DetailViewModel
import com.blogapps.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule  = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { CreateUpdateViewModel(get()) }
}