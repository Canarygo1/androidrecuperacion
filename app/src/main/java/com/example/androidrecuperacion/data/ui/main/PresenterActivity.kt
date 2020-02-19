package com.example.androidrecuperacion.data.ui.main

import com.example.androidrecuperacion.data.local.LocalRepository
import com.example.androidrecuperacion.data.model.Propertis
import com.example.androidrecuperacion.data.model.User
import com.example.androidrecuperacion.data.remote.RemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PresenterActivity(
  private var view: MainView,
  private var remoteRepository: RemoteRepository,
  private var localRepository: LocalRepository
) {
  //  fun buttonClicked(texto: String) {
//    CoroutineScope(Dispatchers.IO).launch {
//      var user = User(texto)
//      val loggedUser = localRepository.setLoggedUser(user)
//      view.gotoHome()
//    }
//  }
  fun atributeChange(eyes: String, mouth: String, nose: String) {
    if (eyes.equals("Select the eyes") || mouth.equals("Select the mouth") || nose.equals("Select the nose")) {

      view.showImage("error")
    } else {
      view.showImage("https://api.adorable.io/avatars/face/${eyes}/${nose}/${mouth}/EDEDFF/200")
    }
  }

  fun saveClicked(username: String, url: String) {
    CoroutineScope(Dispatchers.IO).launch {
      var user = User(username, url)
      val loggedUser = localRepository.setLoggedUser(user)
      view.goToHome()
    }
  }

  fun isUSerLogged() {
    CoroutineScope(Dispatchers.IO).launch {
      val loggedUser = localRepository.getLoggedUser()
      withContext(Dispatchers.Main) {
        if (loggedUser != null) {
          view.goToHome()
        } else {

        }
      }
    }
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
  fun showImage(url: String)
  fun goToHome()
}
