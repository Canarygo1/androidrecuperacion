package com.example.passscreentest.data.remote

import com.example.androidrecuperacion.data.model.Propertis
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface AvatarApi {
  @GET("avatars/list")
  suspend fun getQuestions(): Response<Propertis>
}
object RetrofitFactory {
  const val BASE_URL = "https://api.adorable.io/"

  fun getAvatarApi(): AvatarApi {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create())
      .build().create(AvatarApi::class.java)
  }
}
