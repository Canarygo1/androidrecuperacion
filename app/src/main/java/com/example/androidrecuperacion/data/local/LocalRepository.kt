package com.example.androidrecuperacion.data.local

import com.example.androidrecuperacion.data.model.User

interface LocalRepository {
    suspend fun getLoggedUser(): User?
    suspend fun setLoggedUser(user: User)
    suspend fun deleteLoggedUser()
}