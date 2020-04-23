package com.smartdevservice.mystudiofactory.framework.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

open class BaseViewModel (
    private val dispatcher: CoroutineDispatcher
) : ViewModel(), CoroutineScope {

    private var handler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable -> baseHandleException(throwable) }

    private var job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job + handler


    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
        // Cancel job on activity destroy. After destroy all children jobs will be cancelled automatically
    }


    private fun baseHandleException(throwable: Throwable) {
        Timber.d("Throwable: cause = ${throwable.cause}, message = ${throwable.message}")
    }

}