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

class ActivityAskName : AppCompatActivity() {
    // Variables globales
    lateinit var editTextRespuesta: EditText
    lateinit var buttonNextQuestion: Button
    lateinit var textViewQuestion: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_name)

        editTextRespuesta = findViewById(R.id.editTextRespuesta)
        buttonNextQuestion = findViewById(R.id.buttonNextQuestion)
        textViewQuestion = findViewById(R.id.textViewQuestion)
        textViewQuestion.text = "Introduce tu nombre para saber tus resultados."

        habilitarBtnNext()
    }

    // Funciones
    fun onClickButtonNextQuestion(view: View) {
        Usuario.nombre = editTextRespuesta.text.toString().trim()

        val i = Intent(applicationContext, ActivityFinal::class.java)
        startActivity(i)
    }

    // Función que bloquea el botón atrás
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "No se puede volver atrás", Toast.LENGTH_SHORT).show()
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