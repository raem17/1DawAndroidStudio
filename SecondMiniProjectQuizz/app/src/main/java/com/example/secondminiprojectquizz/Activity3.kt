package com.example.secondminiprojectquizz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class Activity3 : AppCompatActivity() {
    // Variables globales
    lateinit var textViewQuestion: TextView
    lateinit var checkBoxAnswerLeftTop: CheckBox
    lateinit var checkBoxAnswerLeftBot: CheckBox
    lateinit var checkBoxAnswerRightTop: CheckBox
    lateinit var checkBoxAnswerRightBot: CheckBox
    lateinit var buttonNextQuestion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        iniciarVariables()
    }

    // Funciones
    fun onClickButtonNextQuestion(view: View) {
        if (checkBoxAnswerLeftTop.isChecked || checkBoxAnswerLeftBot.isChecked || checkBoxAnswerRightTop.isChecked || checkBoxAnswerRightBot.isChecked) {
            comprobarRespuestas()
            val i = Intent(applicationContext, Activity4::class.java)
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
        textViewQuestion.text = "2. ¿Qué espadas ha usado Kratos a lo largo de la saga griega?\nRespuesta múltiple."

        checkBoxAnswerLeftTop = findViewById(R.id.checkBoxAnswerLeftTop)
        checkBoxAnswerLeftTop.text = "Espadas del exilio."

        checkBoxAnswerLeftBot = findViewById(R.id.checkBoxAnswerLeftBot)
        checkBoxAnswerLeftBot.text = "Espadas del caos."

        checkBoxAnswerRightTop = findViewById(R.id.checkBoxAnswerRightTop)
        checkBoxAnswerRightTop.text = "Espadas de Esparta."

        checkBoxAnswerRightBot = findViewById(R.id.checkBoxAnswerRightBot)
        checkBoxAnswerRightBot.text = "Espadas de Atenea."

        buttonNextQuestion = findViewById(R.id.buttonNextQuestion)
    }

    fun comprobarRespuestas() {
        if (checkBoxAnswerLeftTop.isChecked && checkBoxAnswerLeftBot.isChecked && checkBoxAnswerRightBot.isChecked) {
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