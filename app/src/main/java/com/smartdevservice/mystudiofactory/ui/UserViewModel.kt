package com.smartdevservice.mystudiofactory.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartdevservice.mystudiofactory.data.repository.UsersRepositoryImp
import com.smartdevservice.mystudiofactory.domain.User
import com.smartdevservice.mystudiofactory.framework.viewmodel.BaseViewModel
import com.smartdevservice.mystudiofactory.ui.utils.NetworkUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */


sealed class MyViewModelStateSealed(
    val users: List<User>? = null
)

class MyViewModelStateSuccess(users: List<User>?) : MyViewModelStateSealed(users)

object MyViewModelStateNotFound : MyViewModelStateSealed()

class UserViewModel(
    private val context: Context,
    private val usersRepository: UsersRepositoryImp,
    dispatcher: CoroutineDispatcher
) : BaseViewModel(dispatcher) {

    private val _usersState = MutableLiveData<MyViewModelStateSealed?>()
    val usersState: LiveData<MyViewModelStateSealed?> get() = _usersState

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _isNetworking = MutableLiveData<Boolean>()
    val isNetworking: LiveData<Boolean> get() = _isNetworking

    init {
        launch {
            Timber.d("init::launch")
            if (!NetworkUtils.isConnectingToInternet(context)) {
                _isNetworking.value = false
            }
            val users = usersRepository.getAllUsers()

            when {
                users.isNullOrEmpty() -> {
                    Timber.d("init::launch, sycAllUsers not found")
                    _usersState.value = MyViewModelStateNotFound
                }
                else -> {
                    Timber.d("init::launch, sycAllUsers success")
                    _usersState.value = MyViewModelStateSuccess(users)
                }
            }
        }

    }

    fun syncFullUser() {

        if (!NetworkUtils.isConnectingToInternet(context)) {
            _isNetworking.value = false
            return
        }
        launch {
            Timber.d("syncFullUser::launch")
            val users = usersRepository.syncFullUser()
            when {
                users.isNullOrEmpty() -> {
                    Timber.d("syncFullUser::launch, sync not found")
                    _usersState.value = MyViewModelStateNotFound
                }
                else -> {
                    Timber.d("syncFullUser::launch, sync success")
                    _usersState.value = MyViewModelStateSuccess(users)
                }
            }
        }
    }

    fun findUserById(userId: String?) {
        Timber.d("finUserById, userId = $userId")
        _usersState.value?.users?.let {
            for (user in it) {
                if (user.id == userId) {
                    _user.value = user
                    break
                }
            }
        }
    }
}
