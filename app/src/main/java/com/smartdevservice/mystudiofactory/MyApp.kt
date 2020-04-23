package com.smartdevservice.mystudiofactory

import android.app.Application
import com.smartdevservice.mystudiofactory.framework.di.domainModule
import com.smartdevservice.mystudiofactory.framework.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(vmModule, domainModule))
            if(BuildConfig.DEBUG) {
                androidLogger()
            }
        }

        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())
    }
}