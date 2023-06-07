package com.example.secondminiprojectquizz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Activity2 : AppCompatActivity() {
    // Variables globales
    lateinit var editTextRespuesta: EditText
    lateinit var buttonNextQuestion: Button
    lateinit var textViewQuestion: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        editTextRespuesta = findViewById(R.id.editTextRespuesta)
        buttonNextQuestion = findViewById(R.id.buttonNextQuestion)
        textViewQuestion = findViewById(R.id.textViewQuestion)
        textViewQuestion.text = "1. ¿Cuál es el nombre del padre de Kratos?\nSu nombre está compuesto por 4 letras."

        habilitarBtnNext()
    }

    // Funciones
    fun onClickButtonNextQuestion(view: View) {
        comprobarRespuesta()
        val i = Intent(applicationContext, Activity3::class.java)
        startActivity(i)
    }

    // Función que bloquea el botón atrás
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "No se puede volver atrás", Toast.LENGTH_SHORT).show()
    }

    fun comprobarRespuesta() {
        if (editTextRespuesta.text.toString().equals("zeus", ignoreCase = true)) {
            Usuario.puntuacion += 10
            Usuario.preguntasAcertadas++

            val alertCorrect = Toast.makeText(applicationContext, "Respuesta correcta", Toast.LENGTH_SHORT)
            alertCorrect.show()
        } else {
            Usuario.preguntasFalladas++

            val alertIncorrect = Toast.makeText(applicationContext, "Incorrecto, la respuesta era Zeus", Toast.LENGTH_LONG)
            alertIncorrect.show()
        }
    }

    fun habilitarBtnNext() {
        // "Caja negra"
        editTextRespuesta.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("beforeTextChanged", "not override")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                buttonNextQuestion.isEnabled = editTextRespuesta.text.isNotBlank()
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("afterTextChanged", "not override")
            }

        })
    }
}