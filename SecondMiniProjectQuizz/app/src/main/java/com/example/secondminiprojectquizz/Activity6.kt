package com.example.secondminiprojectquizz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Activity6 : AppCompatActivity() {
    // Variables globales
    lateinit var textViewQuestion: TextView
    lateinit var radioButtonAnswer1: RadioButton
    lateinit var radioButtonAnswer2: RadioButton
    lateinit var radioButtonAnswer3: RadioButton
    lateinit var buttonNextQuestion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6)

        iniciarVariables()
    }

    // Funciones
    fun onClickButtonNextQuestion(view: View) {
        if (radioButtonAnswer1.isChecked || radioButtonAnswer2.isChecked || radioButtonAnswer3.isChecked) {
            comprobarRespuesta()
            val i = Intent(applicationContext, Activity7::class.java)
            startActivity(i)
        } else {
            val alertNoneIsChecked = Toast.makeText(applicationContext, "Seleccione al menos una respuesta", Toast.LENGTH_LONG)
            alertNoneIsChecked.show()
        }
    }

    // Función que bloquea el botón atrás
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "No se puede volver atrás", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    fun iniciarVariables() {
        textViewQuestion = findViewById(R.id.textViewQuestion)
        textViewQuestion.text = "5. ¿Por qué las hermanas del destino fueron derrotadas por Kratos?"

        radioButtonAnswer1 = findViewById(R.id.radioButtonAnswer1)
        radioButtonAnswer1.text = "Porque Kratos contaba con la espada del Olimpo."

        radioButtonAnswer2 = findViewById(R.id.radioButtonAnswer2)
        radioButtonAnswer2.text = "Porque subestimaron al espartano."

        radioButtonAnswer3 = findViewById(R.id.radioButtonAnswer3)
        radioButtonAnswer3.text = "Porque Gaia podía controlar el destino."

        buttonNextQuestion = findViewById(R.id.buttonNextQuestion)
    }

    fun comprobarRespuesta() {
        if (radioButtonAnswer2.isChecked) {
            Usuario.puntuacion += 10
            Usuario.preguntasAcertadas++

            val alertCorrect = Toast.makeText(applicationContext, "Respuesta correcta", Toast.LENGTH_SHORT)
            alertCorrect.show()
        } else {
            Usuario.preguntasFalladas++

            val alertIncorrect = Toast.makeText(applicationContext, "Respuesta incorrecta", Toast.LENGTH_SHORT)
            alertIncorrect.show()
        }
    }
}