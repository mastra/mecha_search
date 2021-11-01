package com.molol.mechasearch.usecase.di

import com.molol.mechasearch.usecase.SearchUseCase
import org.koin.dsl.module

val userCaseModule = module {
    factory { SearchUseCase(get()) }
}