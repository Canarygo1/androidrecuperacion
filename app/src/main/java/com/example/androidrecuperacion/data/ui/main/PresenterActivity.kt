package com.example.androidrecuperacion.data.ui.main

import com.example.androidrecuperacion.data.model.Propertis
import com.example.androidrecuperacion.data.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PresenterActivity(
  private var view: MainView,
  private var remoteRepository: RemoteRepository
) {
  //  fun buttonClicked(texto: String) {
//    CoroutineScope(Dispatchers.IO).launch {
//      var user = User(texto)
//      val loggedUser = localRepository.setLoggedUser(user)
//      view.gotoHome()
//    }
//  }
  fun atributeChange() {

  }
  fun init() {
    CoroutineScope(Dispatchers.IO).launch {
      try {
        val propertis = remoteRepository.getOptions()
        withContext(Dispatchers.Main) {
          if (propertis.face.mouth.isEmpty()) {
            return@withContext
          }
          view.showLists(propertis)
        }
      } catch (e: Exception) {

      }
    }

  }
}

interface MainView {
  fun showLists(propertis: Propertis)
  fun showImage(Url: String)
}
