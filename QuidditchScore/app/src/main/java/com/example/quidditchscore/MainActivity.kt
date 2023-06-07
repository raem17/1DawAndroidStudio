package com.example.quidditchscore

import android.annotation.SuppressLint
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // Se declaran cuatro variables globales
    // Dos int y 2 de tipo textView
    private var contTeamA = 0
    private var contTeamB = 0
    private lateinit var textViewContTeamA : TextView
    private lateinit var textViewContTeamB : TextView


    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Se inicializan las variables de tipo textView
        textViewContTeamA = findViewById(R.id.textViewContTeamA)
        textViewContTeamB = findViewById(R.id.textViewContTeamB)
    }

    // Funciones/Métodos
    fun incrementarMarcadores (view: View) {
        when (view.id) {
            R.id.button10pTeamA -> {
                contTeamA+=10
                textViewContTeamA.text = contTeamA.toString()

            }
            R.id.button5pTeamA -> {
                contTeamA+=5
                textViewContTeamA.text = contTeamA.toString()

            }
            R.id.button10pTeamB -> {
                contTeamB+=10
                textViewContTeamB.text = contTeamB.toString()

            }
            R.id.button5pTeamB -> {
                contTeamB+=5
                textViewContTeamB.text = contTeamB.toString()
            }
        }
    }

    fun reset (view: View) {
        contTeamA = 0
        contTeamB = 0

        textViewContTeamA.text = contTeamA.toString()
        textViewContTeamB.text = contTeamB.toString()
    }

    fun snitchEncontrada (view: View) {
        if (contTeamA > contTeamB) {
            var alertTeamAWin = Toast.makeText(applicationContext, "El equipo A gana el partido, la snitch ha sido encontrada.", Toast.LENGTH_LONG)
            alertTeamAWin.show()

        } else if (contTeamB > contTeamA) {
            var alertTeamBWin = Toast.makeText(applicationContext, "El equipo B gana el partido, la snitch ha sido encontrada.", Toast.LENGTH_LONG)
            alertTeamBWin.show()

        } else {
            var alertEmpate = Toast.makeText(applicationContext, "Ha habido un empate, la snitch ha sido encontrada.", Toast.LENGTH_LONG)
            alertEmpate.show()
        }

        reset(view)
    }
}