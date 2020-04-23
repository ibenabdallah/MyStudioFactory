package com.smartdevservice.mystudiofactory.framework.di

import com.smartdevservice.mystudiofactory.data.datasource.RemoteDataSource
import com.smartdevservice.mystudiofactory.data.repository.UsersRepository
import com.smartdevservice.mystudiofactory.data.repository.UsersRepositoryImp
import com.smartdevservice.mystudiofactory.framework.network.*
import com.smartdevservice.mystudiofactory.ui.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

val domainModule = module {

    single<UsersRepository> { UsersRepositoryImp(get()) }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    single { initOkHttpClient() }

    single<ApiService> { initRetrofitClient(get(), BASE_API_URL) }

    factory { UsersRepositoryImp(get()) }

    single<CoroutineDispatcher> { Dispatchers.Main }
}

val vmModule = module {
    viewModel { UserViewModel(get(), get()) }
}