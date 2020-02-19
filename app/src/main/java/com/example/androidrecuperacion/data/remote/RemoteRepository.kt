package com.example.androidrecuperacion.data.remote

import com.example.androidrecuperacion.data.model.Propertis

interface RemoteRepository {
  suspend fun getOptions(): Propertis

}
