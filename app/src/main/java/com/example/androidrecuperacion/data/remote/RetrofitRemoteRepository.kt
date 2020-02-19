package com.example.androidrecuperacion.data.remote

import android.accounts.NetworkErrorException
import com.example.androidrecuperacion.data.model.Propertis
import com.example.passscreentest.data.remote.AvatarApi

class RetrofitRemoteRepository(val avatarApi: AvatarApi):RemoteRepository{
  override suspend fun getOptions(): Propertis {
    val response = avatarApi.getQuestions()
    if (response.isSuccessful) {
      println("ok")
      println(response.body()!!)
      return response.body()!!
    } else {
      throw NetworkErrorException() as Throwable
    }
  }
}
