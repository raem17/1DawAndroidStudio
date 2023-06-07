package com.example.elvishtranslator

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ActivityNumeros : AppCompatActivity() {
    // Variables globales
    lateinit var listViewListOfNumbers: ListView
    val numbersList = arrayListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numeros)
        supportActionBar?.title = "Números"

        listViewListOfNumbers = findViewById(R.id.listViewListOfNumbers)

        paintListViewListOfNumbers()
        setearOnItemClickListener()
    }

    // Funciones
    private fun paintListViewListOfNumbers () {
        val number_1 = Word("Uno", "Min", R.drawable.one, R.raw.uno)
        val number_2 = Word("Dos", "Tâd", R.drawable.two, R.raw.dos)
        val number_3 = Word("Tres", "Nêl", R.drawable.three, R.raw.tres)
        val number_4 = Word("Cuatro", "Canad", R.drawable.four, R.raw.cuatro)
        val number_5 = Word("Cinco", "Leben", R.drawable.five, R.raw.cinco)
        val number_6 = Word("Seis", "Eneg", R.drawable.six, R.raw.audio_de_prueba)
        val number_7 = Word("Siete", "Odog", R.drawable.seven, R.raw.audio_de_prueba)
        val number_8 = Word("Ocho", "Tolodh", R.drawable.eight, R.raw.audio_de_prueba)
        val number_9 = Word("Nueve", "Neder", R.drawable.nine, R.raw.audio_de_prueba)
        val number_10 = Word("Diez", "Pae", R.drawable.ten, R.raw.audio_de_prueba)

        numbersList.addAll(listOf(number_1, number_2, number_3, number_4, number_5, number_6, number_7, number_8,
            number_9, number_10))

        val numbersArrayAdapter = WordArrayAdapter(applicationContext, R.layout.item_list_words, numbersList)
        listViewListOfNumbers.adapter = numbersArrayAdapter
    }

    fun setearOnItemClickListener() {
        listViewListOfNumbers.setOnItemClickListener { parent, view, position, id ->
            val currentNumber: Word = numbersList[position]
            val mp: MediaPlayer = MediaPlayer.create(applicationContext, currentNumber.audioID)
            mp.start()
        }
    }
}