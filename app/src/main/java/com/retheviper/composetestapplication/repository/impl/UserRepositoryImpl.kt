package com.retheviper.composetestapplication.repository.impl

import com.retheviper.composetestapplication.data.RandomUserInfo
import com.retheviper.composetestapplication.data.User
import com.retheviper.composetestapplication.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.net.URL
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val format = Json { isLenient = true }

    override suspend fun getRandomUsers(items: Int): List<User> =
        withContext(Dispatchers.IO) {
            getRandomUserInfo(items).results.map {
                User(
                    name = "${it.name.first} ${it.name.last}",
                    location = "${it.location.city}, ${it.location.country}",
                    profileImageMedium = it.picture.medium
                )
            }
        }

    private fun getRandomUserInfo(items: Int): RandomUserInfo {
        val connection =
            URL("https://randomuser.me/api/?results=$items").openConnection()
        val json = connection.getInputStream().bufferedReader().use(BufferedReader::readText)
        return json.let(format::decodeFromString)
    }
}