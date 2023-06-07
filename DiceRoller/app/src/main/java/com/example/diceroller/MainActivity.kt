package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var shownImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shownImage = findViewById<ImageView>(R.id.imageViewDice)
    }

    fun rollDice(vista: View) {

        var numRandom = (1..6).random()

        when (numRandom) {
            1 -> {
                var changeImage = shownImage.setImageResource(R.drawable.dice_1)
            }
            2 -> {
                var changeImage = shownImage.setImageResource(R.drawable.dice_2)
            }
            3 -> {
                var changeImage = shownImage.setImageResource(R.drawable.dice_3)
            }
            4 -> {
                var changeImage = shownImage.setImageResource(R.drawable.dice_4)
            }
            5 -> {
                var changeImage = shownImage.setImageResource(R.drawable.dice_5)
            }
            6 -> {
                var changeImage = shownImage.setImageResource(R.drawable.dice_6)
            }
        }
    }
}