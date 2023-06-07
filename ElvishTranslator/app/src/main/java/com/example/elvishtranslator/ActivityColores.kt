package com.example.elvishtranslator

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ActivityColores : AppCompatActivity() {
    // Variables globales
    lateinit var listViewListOfColors: ListView
    val colorsList = arrayListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colores)

        listViewListOfColors = findViewById(R.id.listViewListOfColors)

        paintListViewListOfColors()
        setearOnItemClickListener()
    }

    // Funciones
    private fun paintListViewListOfColors () {
        val color_red = Word("Rojo", "Caran", R.drawable.red, R.raw.rojo)
        val color_carmine = Word("Carmín", "Carnin", R.drawable.carmine, R.raw.carmin)
        val color_navy_blue = Word("Azul Marino", "Luin", R.drawable.navy_blue, R.raw.azul_marino)
        val color_blue = Word("Azul", "Elu", R.drawable.blue, R.raw.azul)
        val color_yellow = Word("Amarillo", "Malen", R.drawable.yellow, R.raw.amarillo)
        val color_green = Word("Verde", "Calen", R.drawable.green, R.raw.audio_de_prueba)
        val color_black = Word("Negro", "Morn", R.drawable.black, R.raw.audio_de_prueba)
        val color_dark_brown = Word("Marrón oscuro", "Baran", R.drawable.dark_brown, R.raw.audio_de_prueba)
        val color_brown = Word("Marrón", "Rhosg", R.drawable.brown, R.raw.audio_de_prueba)
        val color_white = Word("Blanco", "Faen", R.drawable.white, R.raw.audio_de_prueba)

        colorsList.addAll(listOf(color_red, color_carmine, color_navy_blue, color_blue, color_yellow, color_green, color_black, color_dark_brown, color_brown,
        color_white))

        val colorsListArrayAdapter = WordArrayAdapter(applicationContext, R.layout.item_list_words, colorsList)
        listViewListOfColors.adapter = colorsListArrayAdapter
    }

    fun setearOnItemClickListener() {
        listViewListOfColors.setOnItemClickListener { parent, view, position, id ->
            val currentColor: Word = colorsList[position]
            val mp: MediaPlayer = MediaPlayer.create(applicationContext, currentColor.audioID)
            mp.start()
        }
    }
}