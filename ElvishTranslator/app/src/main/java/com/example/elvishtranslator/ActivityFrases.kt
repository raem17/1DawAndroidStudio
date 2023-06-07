package com.example.elvishtranslator

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.thedeanda.lorem.LoremIpsum

class ActivityFrases : AppCompatActivity() {
    // Variables globales
    lateinit var listViewListOfPhrases: ListView
    val listOfPhrases = arrayListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frases)
        supportActionBar?.title = "Frases"

        listViewListOfPhrases = findViewById(R.id.listViewListOfPhrases)

        paintListViewListOfPhrases()
        setearOnItemClickListener()
    }

    // Funciones
    private fun paintListViewListOfPhrases () {
        val loremIpsum: LoremIpsum = LoremIpsum.getInstance()

        val phrase_1 = Word(loremIpsum.getWords(5, 10), loremIpsum.getWords(5, 10), R.drawable.phrase, R.raw.audio_de_prueba)
        val phrase_2 = Word(loremIpsum.getWords(5, 10), loremIpsum.getWords(5, 10), R.drawable.phrase, R.raw.audio_de_prueba)
        val phrase_3 = Word(loremIpsum.getWords(5, 10), loremIpsum.getWords(5, 10), R.drawable.phrase, R.raw.audio_de_prueba)
        val phrase_4 = Word(loremIpsum.getWords(5, 10), loremIpsum.getWords(5, 10), R.drawable.phrase, R.raw.audio_de_prueba)
        val phrase_5 = Word(loremIpsum.getWords(5, 10), loremIpsum.getWords(5, 10), R.drawable.phrase, R.raw.audio_de_prueba)
        val phrase_6 = Word(loremIpsum.getWords(5, 10), loremIpsum.getWords(5, 10), R.drawable.phrase, R.raw.audio_de_prueba)
        val phrase_7 = Word(loremIpsum.getWords(5, 10), loremIpsum.getWords(5, 10), R.drawable.phrase, R.raw.audio_de_prueba)
        val phrase_8 = Word(loremIpsum.getWords(5, 10), loremIpsum.getWords(5, 10), R.drawable.phrase, R.raw.audio_de_prueba)

        listOfPhrases.addAll(listOf(phrase_1, phrase_2, phrase_3 ,phrase_4, phrase_5, phrase_6, phrase_7, phrase_8))

        val listOfPhrasesArrayAdapter = WordArrayAdapter(applicationContext, R.layout.item_list_words, listOfPhrases)
        listViewListOfPhrases.adapter = listOfPhrasesArrayAdapter
    }

    fun setearOnItemClickListener() {
        listViewListOfPhrases.setOnItemClickListener { parent, view, position, id ->
            val currentPhrase: Word = listOfPhrases[position]
            val mp: MediaPlayer = MediaPlayer.create(applicationContext, currentPhrase.audioID)
            mp.start()
        }
    }
}