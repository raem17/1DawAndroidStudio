package com.example.forocoches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // Variables globales
    private lateinit var editTextCadena: EditText
    private lateinit var buttonLimpiarCadena: Button
    private lateinit var textViewCadenaLimpia: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find view
        buttonLimpiarCadena = findViewById(R.id.buttonLimpiarCadena)
        textViewCadenaLimpia = findViewById(R.id.textViewCadenaLimpia)
        editTextCadena = findViewById(R.id.editTextCadena)

        buttonLimpiarCadena.setOnClickListener {
            devolverCodigoLimpio()
        }
    }

    // Funciones

    private fun devolverCodigoLimpio () {
        val codigoSucio = editTextCadena.text.toString().trim()

        // 1ª parte
        val codigoLimpiov1 = codigoSucio.replace(".", "")

        // 2ª parte
        var codigoLimpiov2 = ""
        for (i in 1 until codigoLimpiov1.length-1) {
            // Si es minúscula convierte la letra a mayúscula
            if (esMinuscula(codigoLimpiov1[i])) {
                codigoLimpiov2 += codigoLimpiov1[i].uppercaseChar()

            } else if (esMayuscula(codigoLimpiov1[i])) {
                codigoLimpiov2 += codigoLimpiov1[i].lowercaseChar()
            }
        }

        textViewCadenaLimpia.text = codigoLimpiov2
    }

    private fun esMinuscula (letra: Char): Boolean {
        return letra == letra.lowercaseChar()
    }

    private fun esMayuscula (letra: Char): Boolean {
        return letra == letra.uppercaseChar()
    }
}