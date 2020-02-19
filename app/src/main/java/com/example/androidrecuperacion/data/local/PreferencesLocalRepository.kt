package com.example.androidrecuperacion.data.local

import android.content.SharedPreferences
import com.example.androidrecuperacion.data.model.User

class PreferencesLocalRepository(val sharedPreferences: SharedPreferences) : LocalRepository {


    override suspend fun setLoggedUser(user: User) {
        val editor = sharedPreferences.edit()
        editor.putString(usernameKey, user.username)
        editor.putString(urlKey, user.url)
        editor.apply()
    }


    val usernameKey = "username"
    val urlKey = "url"
    override suspend fun getLoggedUser(): User? {
        val username = sharedPreferences.getString(usernameKey, null)
        val url = sharedPreferences.getString(urlKey, null)

        if (username != null) {
            return User(username, url!!)
        } else {
            return null
        }
    }


    override suspend fun deleteLoggedUser() {

    }
}