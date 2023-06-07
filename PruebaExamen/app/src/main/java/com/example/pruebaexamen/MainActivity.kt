package com.example.pruebaexamen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Variables globales
    lateinit var editTextNombre: EditText
    lateinit var editTextCorreo: EditText
    lateinit var editTextNumero: EditText
    lateinit var buttonEnviar: Button

    // onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarVariables()
        setButtonListener()
    }

    // Funciones

    fun inicializarVariables() {
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        editTextNumero = findViewById(R.id.editTextNumero)
        buttonEnviar = findViewById(R.id.buttonEnviar)
    }

    fun setButtonListener() {
        buttonEnviar.setOnClickListener {
            if (nombreYTelefonoValidos() && correoValido()) {
                val correos = arrayOf("alex@gmail.com")
                composeEmail(correos, "Asunto importante", "Hola qué haces ?")

            } else {
                Toast.makeText(applicationContext, "Formato incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun nombreYTelefonoValidos(): Boolean {
        var sonValidos = false

        if (editTextNombre.text.toString().isNotBlank() && editTextNumero.text.toString().contains("+34")) {
            sonValidos = true

        }
        return sonValidos
    }

    fun correoValido(): Boolean {
        var esValido = true
        val charsProhibidos = "\\\"ºª!|·#$%&/()='?¡¿€`^[+*]´¨{ç},;:-_<> -"
        val correo = editTextCorreo.text.toString().trim()

        if (correo.isNotBlank() && correo.contains("@")) {
            for (i in 0 until charsProhibidos.length) {
                if (correo.contains(charsProhibidos[i])) {
                    esValido = false
                    break
                }
            }
        } else {
            esValido = false
        }

        return esValido
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun composeEmail(addresses: Array<String>, subject: String, body: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}

















