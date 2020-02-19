package com.example.androidrecuperacion.data.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.androidrecuperacion.R
import com.example.androidrecuperacion.data.local.PreferencesLocalRepository
import com.example.androidrecuperacion.data.model.Propertis
import com.example.androidrecuperacion.data.remote.RemoteRepository
import com.example.androidrecuperacion.data.remote.RetrofitRemoteRepository
import com.example.androidrecuperacion.data.ui.home.HomeActivity
import com.example.passscreentest.data.remote.RetrofitFactory
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), MainView {
  override fun goToHome() {
    val intent = Intent(this, HomeActivity::class.java)
    finish()
    startActivity(intent)
  }

  override fun showImage(url: String) {
    this.url = url
    if (!url.equals("error")) {
      Picasso.get()
        .load(url)
        .resize(50, 50)
        .centerCrop()
        .into(avatarImage)
    } else {
      avatarImage.setImageResource(0)
    }
  }

  private lateinit var url: String
  private lateinit var avatarImage: ImageView
  private lateinit var usernameTxt: TextInputEditText
  private lateinit var eyeSpinner: Spinner
  private lateinit var noseSpinner: Spinner
  private lateinit var mouthSpinner: Spinner
  private lateinit var presenter: PresenterActivity
  lateinit var mouthSelected: String
  lateinit var noseSelected: String
  lateinit var eyeSelected: String
  override fun showLists(propertis: Propertis) {
    propertis.face.eyes.add(0, "Select the eyes")
    propertis.face.nose.add(0, "Select the nose")
    propertis.face.mouth.add(0, "Select the mouth")

    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, propertis.face.eyes)
    eyeSpinner.adapter = adapter
    val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, propertis.face.nose)
    noseSpinner.adapter = adapter2
    val adapter3 = ArrayAdapter(this, android.R.layout.simple_list_item_1, propertis.face.mouth)
    mouthSpinner.adapter = adapter3
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.appbar_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.sendMenuOption -> presenter.saveClicked(usernameTxt.text.toString(), url)

    }
    return true
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mouthSelected = ""
    noseSelected = ""
    eyeSelected = ""
    url = ""
    usernameTxt = findViewById(R.id.name)
    eyeSpinner = findViewById(R.id.eyes)
    noseSpinner = findViewById(R.id.nose)
    mouthSpinner = findViewById(R.id.mouth)
    avatarImage = findViewById(R.id.avatarImage)
    val localRepository =
      PreferencesLocalRepository(
        getSharedPreferences(
          "login_preference",
          Context.MODE_PRIVATE
        )
      )

    eyeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        eyeSelected = parent.getItemAtPosition(position).toString()
        presenter.atributeChange(eyeSelected, mouthSelected, noseSelected)
      }

      override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
      }
    }
    noseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        noseSelected = parent.getItemAtPosition(position).toString()
        presenter.atributeChange(eyeSelected, mouthSelected, noseSelected)
      }

      override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
      }
    }
    mouthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        mouthSelected = parent.getItemAtPosition(position).toString()
        presenter.atributeChange(eyeSelected, mouthSelected, noseSelected)
      }

      override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
      }
    }
    val remoteRepository: RemoteRepository =
      RetrofitRemoteRepository(RetrofitFactory.getAvatarApi())
    presenter = PresenterActivity(this, remoteRepository, localRepository)
    presenter.isUSerLogged()
    presenter.init()
  }
}
