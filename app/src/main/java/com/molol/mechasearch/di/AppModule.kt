package com.molol.mechasearch.di

import com.molol.mechasearch.ui.detail.DetailViewModel
import com.molol.mechasearch.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SearchViewModel() }
    viewModel { DetailViewModel() }
}