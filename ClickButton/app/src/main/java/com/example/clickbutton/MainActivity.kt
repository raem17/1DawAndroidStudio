package com.example.clickbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var shownImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shownImage = findViewById<ImageView>(R.id.imageViewAnimal)
    }

        fun changeImage(vista: View) {

        if (vista.id == R.id.buttonDogToMonkey) {
            var dogToMonkey = shownImage.setImageResource(R.drawable.monkey)
        }

        if (vista.id == R.id.buttonMonkeyToDog) {
            var monkeyToDog = shownImage.setImageResource(R.drawable.dog)
        }
    }
}