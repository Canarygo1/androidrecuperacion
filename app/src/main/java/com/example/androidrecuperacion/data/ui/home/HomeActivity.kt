package com.example.androidrecuperacion.data.ui.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.androidrecuperacion.R
import com.example.androidrecuperacion.data.local.PreferencesLocalRepository
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity(), HomeView {


    private lateinit var avatarImage: ImageView
    private lateinit var username: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        avatarImage = findViewById(R.id.imageView2)
        username = findViewById(R.id.textView)
        val localRepository =
            PreferencesLocalRepository(
                getSharedPreferences(
                    "login_preference",
                    Context.MODE_PRIVATE
                )
            )
        val presenter = HomePresenter(this, localRepository)
        presenter.init()
    }

    override fun showData(name: String, url: String) {
        username.text = name
        println(url)
        Picasso.get()
            .load(url)
            .resize(50, 50)
            .centerCrop()
            .into(avatarImage)
    }
}
