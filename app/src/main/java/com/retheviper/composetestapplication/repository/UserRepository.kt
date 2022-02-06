package com.retheviper.composetestapplication.repository

import com.retheviper.composetestapplication.data.User

interface UserRepository {

    suspend fun getRandomUsers(items: Int): List<User>
}