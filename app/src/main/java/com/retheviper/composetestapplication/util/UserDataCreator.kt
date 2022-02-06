package com.retheviper.composetestapplication.util

import com.retheviper.composetestapplication.data.User

object UserDataCreator {

    fun getLoadingUsers(): List<User> =
        "Loading...".let { loading ->
            (0..10).map {
                User(
                    name = loading,
                    location = loading,
                    profileImageMedium = ""
                )
            }
        }
}