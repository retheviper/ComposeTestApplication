package com.retheviper.composetestapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.retheviper.composetestapplication.data.User
import com.retheviper.composetestapplication.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private var userList = liveData { emit(repository.getRandomUsers(10)) }

    val currentValue: LiveData<List<User>>
        get() = userList

    fun updateValue(items: Int) {
        userList = liveData { emit(repository.getRandomUsers(items)) }
    }
}