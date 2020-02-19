package com.example.androidrecuperacion.data.ui.home

import com.example.androidrecuperacion.data.local.LocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter(val view: HomeView, val localRepository: LocalRepository) {
    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            val loggedUser = localRepository.getLoggedUser()
            withContext(Dispatchers.Main) {
                view.showData(loggedUser!!.username, loggedUser.url)
            }
        }
    }
}

interface HomeView {
    fun showData(name: String, url: String)
}