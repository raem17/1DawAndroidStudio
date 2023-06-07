package com.example.elvishtranslator

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ActivityFamilia : AppCompatActivity() {
    // Variables globales
    lateinit var listViewFamilyList: ListView
    val familyList = arrayListOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_familia)
        supportActionBar?.title = "Familia"

        listViewFamilyList = findViewById(R.id.listViewFamilyList)

        paintListViewFamilyList()
        setearOnItemClickListener()
    }

    // Funciones
    private fun paintListViewFamilyList () {
        val mother = Word("Madre", "Naneth", R.drawable.madre, R.raw.madre)
        val father = Word("Padre", "Adanadar", R.drawable.padre, R.raw.padre)
        val son = Word("Hijo", "Ionn", R.drawable.hijo, R.raw.hijo)
        val daughter = Word("Hija", "Sell", R.drawable.hija, R.raw.hija)
        val grandfather = Word("Abuelo", "Dâd", R.drawable.abuelo, R.raw.abuelo)
        val brother = Word("Hermano", "Muindor", R.drawable.familia, R.raw.audio_de_prueba)
        val sister = Word("Hermana", "Muinthel", R.drawable.familia, R.raw.audio_de_prueba)
        val grandmother = Word("Abuela", "Mam", R.drawable.familia, R.raw.audio_de_prueba)
        val nephew = Word("Sobrino/a", "Hwion", R.drawable.familia, R.raw.audio_de_prueba)
        val wife = Word("Esposa", "Bess", R.drawable.familia, R.raw.audio_de_prueba)

        familyList.addAll(listOf(mother, father, son, daughter, grandfather, brother, sister, grandmother, nephew, wife))

        val familyListArrayAdapter = WordArrayAdapter(applicationContext, R.layout.item_list_words, familyList)
        listViewFamilyList.adapter = familyListArrayAdapter
    }

    fun setearOnItemClickListener() {
        listViewFamilyList.setOnItemClickListener { parent, view, position, id ->
            val currentFamiliar: Word = familyList[position]
            val mp: MediaPlayer = MediaPlayer.create(applicationContext, currentFamiliar.audioID)
            mp.start()
        }
    }
}