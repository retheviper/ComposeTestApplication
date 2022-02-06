package com.retheviper.composetestapplication.util

import junit.framework.Assert.assertEquals
import org.junit.Test

class UserDataCreatorTest {

    @Test
    fun getLoadingUsersTest() {
        val actual = 10
        val users = UserDataCreator.getLoadingUsers()
        assertEquals(actual, users.size)
    }
}