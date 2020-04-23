package com.smartdevservice.mystudiofactory.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartdevservice.mystudiofactory.data.repository.UsersRepositoryImp
import com.smartdevservice.mystudiofactory.domain.User
import com.smartdevservice.mystudiofactory.framework.viewmodel.BaseViewModel
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
    private val usersRepository: UsersRepositoryImp,
    dispatcher: CoroutineDispatcher
) : BaseViewModel(dispatcher) {

    private val _usersState = MutableLiveData<MyViewModelStateSealed?>()
    val usersState: LiveData<MyViewModelStateSealed?> get() = _usersState

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user


    init {
        launch {
            Timber.d("init::launch")

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

        Timber.d("syncFullUser::launch")
        launch {
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
