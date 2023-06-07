package com.example.secondminiprojectquizz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ActivityFinal : AppCompatActivity() {
    // Variables globales
    lateinit var textViewResultados: TextView
    lateinit var buttonShare: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        buttonShare = findViewById(R.id.buttonShare)

        textViewResultados = findViewById(R.id.textViewResultados)
        textViewResultados.text = Usuario.nombre + ",\n\nHas obtenido " + Usuario.puntuacion + " puntos de 60.\n\nPreguntas acertadas: " +
                Usuario.preguntasAcertadas + ".\n\nPreguntas falladas: " + Usuario.preguntasFalladas + ".\n\nGracias por jugar."
    }

    fun compartirResultados(view: View) {
        val resultados = "En el quiz de God of War he obtenido " + Usuario.puntuacion + " puntos de 60.\n\nHe acertado " + Usuario.preguntasAcertadas + " preguntas.\n\nHe fallado " +
                Usuario.preguntasFalladas + " preguntas."

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, resultados)
        intent.type = "text/plain"

        startActivity(Intent.createChooser(intent, "Comparte vía:"))
    }

    fun partidaNueva(view: View) {
        val i = Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
    }

    // Función que bloquea el botón atrás
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "No se puede volver atrás", Toast.LENGTH_SHORT).show()
    }
}