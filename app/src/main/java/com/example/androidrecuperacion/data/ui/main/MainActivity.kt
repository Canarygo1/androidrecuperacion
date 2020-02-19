package com.example.androidrecuperacion.data.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.androidrecuperacion.R
import com.example.androidrecuperacion.data.model.Propertis
import com.example.androidrecuperacion.data.remote.RemoteRepository
import com.example.androidrecuperacion.data.remote.RetrofitRemoteRepository
import com.example.passscreentest.data.remote.RetrofitFactory
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), MainView {
  override fun showImage(Url: String) {

  }

  private lateinit var usernameTxt: TextInputEditText
  private lateinit var eyeSpinner: Spinner
  private lateinit var noseSpinner: Spinner
  private lateinit var mouthSpinner: Spinner
  private lateinit var presenter: PresenterActivity
  lateinit var mouthSelected:String
  lateinit var noseSelected:String
  lateinit var eyeSelected:String
  override fun showLists(propertis: Propertis) {
    propertis.face.eyes.add(0,"Select the eyes" )
    propertis.face.nose.add(0,"Select the nose" )
    propertis.face.mouth.add(0,"Select the mouth" )

    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, propertis.face.eyes)
    eyeSpinner.adapter = adapter
    val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, propertis.face.nose)
    noseSpinner.adapter = adapter2
    val adapter3 = ArrayAdapter(this, android.R.layout.simple_list_item_1, propertis.face.mouth)
    mouthSpinner.adapter = adapter3
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    usernameTxt = findViewById(R.id.textInputEditText4)
    eyeSpinner = findViewById(R.id.spinner)
    noseSpinner = findViewById(R.id.spinner2)
    mouthSpinner = findViewById(R.id.spinner3)
//    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, emptyList)
//    eyeSpinner.adapter = adapter
//    val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, emptyList)
//    noseSpinner.adapter = adapter2
    eyeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        println(parent.getItemAtPosition(position))
      }

      override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
      }
    }
    noseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        println(parent.getItemAtPosition(position))
      }

      override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
      }
    }
    mouthSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        println(parent.getItemAtPosition(position))
      }

      override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
      }
    }
    val remoteRepository: RemoteRepository =
      RetrofitRemoteRepository(RetrofitFactory.getAvatarApi())
    presenter = PresenterActivity(this, remoteRepository)
    presenter.init()
  }
}
