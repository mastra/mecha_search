package com.molol.mechasearch.data.di

import com.molol.mechasearch.data.api.ApiService
import com.molol.mechasearch.data.database.SearchDatabase
import com.molol.mechasearch.data.repository.ItemRepositoryCacheImpl
import com.molol.mechasearch.data.repository.ItemRepositoryApiImpl
import com.molol.mechasearch.data.repository.ItemRepositoryDatabaseImpl
import com.molol.mechasearch.domain.repository.ItemRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single { ApiService.getService() }

    single { ItemRepositoryApiImpl(get()) }
    single { ItemRepositoryDatabaseImpl(get()) }
    single<ItemRepository> { ItemRepositoryCacheImpl(get(), get()) }
    single { SearchDatabase.getDatabase(androidApplication()) }
}