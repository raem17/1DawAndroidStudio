package com.example.petdaycarekotandfire

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddPetActivity : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextRaza: EditText
    private lateinit var spinnerSex: Spinner
    private lateinit var editTextPeso: EditText
    private lateinit var buttonCreate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pet)

        inicializarVariables()
        makeSpinnerSexDropdown()
        setearListeners()
    }

    private fun inicializarVariables() {
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextRaza = findViewById(R.id.editTextRaza)
        spinnerSex = findViewById(R.id.spinnerSex)
        editTextPeso = findViewById(R.id.editTextPeso)

        buttonCreate = findViewById(R.id.buttonCreate)
    }

    private fun makeSpinnerSexDropdown () {
        val sexes = resources.getStringArray(R.array.sexes)
        val sexesArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sexes)
        spinnerSex.adapter = sexesArrayAdapter
    }

    private fun setearListeners() {
        buttonCreate.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            val raza = editTextRaza.text.toString().trim()
            val sexo = spinnerSex.selectedItem.toString()
            val pesoStr = editTextPeso.text.toString()

            if (nombre.isNotBlank() && raza.isNotBlank() && pesoStr.isNotBlank()) {
                val peso = pesoStr.toDouble()
                addPet(nombre, raza, sexo, peso)

            } else {
                val message = Toast.makeText(applicationContext, "Por favor complete todos los campos para continuar.", Toast.LENGTH_LONG)
                message.show()
            }
        }
    }

    private fun addPet(nombre: String, raza: String, sexo: String, peso: Double) {
        val pet = hashMapOf(
            "nombre" to nombre,
            "raza" to raza,
            "sexo" to sexo,
            "peso" to peso
        )

        val db = Firebase.firestore
        db.collection("pets")
            .add(pet)
            .addOnSuccessListener { documentReference ->
                val message = Toast.makeText(applicationContext, "La mascota se ha creado con éxito.\nSu ID es: ${documentReference.id}", Toast.LENGTH_LONG)
                message.show()
            }
            .addOnFailureListener { e ->
                showAlert("No se ha podido crear la mascota.", "Ha ocurrido un fallo inesperado en el servidor.")

                // Para depuración
                Log.w("Error", "$e")
            }
    }

    private fun showAlert(title: String, body: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(body)
        builder.setPositiveButton("Aceptar", null)

        val dialog = builder.create()
        dialog.show()
    }
}