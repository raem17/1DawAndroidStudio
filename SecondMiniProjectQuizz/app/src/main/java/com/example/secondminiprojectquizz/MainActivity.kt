package com.example.secondminiprojectquizz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Usuario.nombre = ""
        Usuario.preguntasFalladas = 0
        Usuario.preguntasAcertadas = 0
        Usuario.puntuacion = 0
    }

    fun onClickButtonStart (view: View) {
        // Pasar a otra vista
        val i = Intent(applicationContext, Activity2::class.java)
        startActivity(i)
    }
}