package com.example.elvishtranslator

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Variables globales
    lateinit var numerosContainer: RelativeLayout
    lateinit var familiaContainer: RelativeLayout
    lateinit var coloresContainer: RelativeLayout
    lateinit var frasesContainer: RelativeLayout

    // onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarVariablesGlobales()
        setearListeners()
    }

    // Fuciones
    fun inicializarVariablesGlobales() {
        numerosContainer = findViewById(R.id.numerosContainer)
        familiaContainer = findViewById(R.id.familiaContainer)
        coloresContainer = findViewById(R.id.coloresContainer)
        frasesContainer = findViewById(R.id.frasesContainer)
    }

    fun setearListeners() {
        numerosContainer.setOnClickListener {
            val intent = Intent(applicationContext, ActivityNumeros::class.java)
            startActivity(intent)
        }

        familiaContainer.setOnClickListener {
            val intent = Intent(applicationContext, ActivityFamilia::class.java)
            startActivity(intent)
        }

        coloresContainer.setOnClickListener {
            val intent = Intent(applicationContext, ActivityColores::class.java)
            startActivity(intent)
        }

        frasesContainer.setOnClickListener {
            val intent = Intent(applicationContext, ActivityFrases::class.java)
            startActivity(intent)
        }
    }

}