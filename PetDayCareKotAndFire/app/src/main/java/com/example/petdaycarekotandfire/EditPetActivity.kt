@file:Suppress("DEPRECATION")

package com.example.petdaycarekotandfire

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditPetActivity : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextRaza: EditText
    private lateinit var spinnerSex: Spinner
    private lateinit var editTextPeso: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pet)

        inicializarVariables()
        makeSpinnerSexDropdown()
        showDataOfSelectedPet()
        setButtonSaveOnClickListener()
    }

    private fun inicializarVariables() {
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextRaza = findViewById(R.id.editTextRaza)
        spinnerSex = findViewById(R.id.spinnerSex)
        editTextPeso = findViewById(R.id.editTextPeso)

        buttonSave = findViewById(R.id.buttonSave)
    }

    private fun makeSpinnerSexDropdown () {
        val sexes = resources.getStringArray(R.array.sexes)
        val sexesArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sexes)
        spinnerSex.adapter = sexesArrayAdapter
    }

    private fun setButtonSaveOnClickListener() {
        buttonSave.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            val raza = editTextRaza.text.toString().trim()
            val sexo = spinnerSex.selectedItem.toString()
            val pesoStr = editTextPeso.text.toString()

            if (nombre.isNotBlank() && raza.isNotBlank() && pesoStr.isNotBlank()) {
                val selectedPet = intent.getSerializableExtra("selectedPet") as Pet
                val documentId = selectedPet.documentID
                val peso = pesoStr.toDouble()

                updatePet(documentId, nombre, raza, sexo, peso)

            } else {
                val message = Toast.makeText(applicationContext, "Por favor complete todos los campos para continuar.", Toast.LENGTH_LONG)
                message.show()
            }
        }
    }

    private fun showDataOfSelectedPet() {
        val selectedPet = intent.getSerializableExtra("selectedPet") as Pet

        editTextNombre.setText(selectedPet.nombre)
        editTextRaza.setText(selectedPet.raza)
        editTextPeso.setText(selectedPet.peso.toString())

        if (selectedPet.sexo.toString().equals("femenino", ignoreCase = true)) {
            spinnerSex.setSelection(1)
        }
    }

    private fun updatePet(documentId: String, nombre: String, raza: String, sexo: String, peso: Double) {
        val pet = hashMapOf(
            "nombre" to nombre,
            "raza" to raza,
            "sexo" to sexo,
            "peso" to peso
        )

        val db = Firebase.firestore
        db.collection("pets").document(documentId)
            .set(pet)
            .addOnSuccessListener {
                val message = Toast.makeText(applicationContext, "La mascota se ha actualizado con éxito.", Toast.LENGTH_LONG)
                message.show()
            }
            .addOnFailureListener { e ->
                showAlert("No se ha podido actualizar la mascota.", "Ha ocurrido un fallo inesperado en el servidor.")

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.itemEliminar) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("¿Eliminar mascota?")
            builder.setMessage("¿De verdad desea eliminar la mascota? Este proceso no se puede deshacer.")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Eliminar") { dialogInterface, i -> deletePet() }

            val dialog = builder.create()
            dialog.show()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deletePet() {
        val selectedPet = intent.getSerializableExtra("selectedPet") as Pet
        val db = Firebase.firestore

        db.collection("pets").document(selectedPet.documentID)
            .delete()
            .addOnSuccessListener {
                val message = Toast.makeText(applicationContext, "La mascota se ha eliminado con éxito.", Toast.LENGTH_LONG)
                message.show()

                val changeView = Intent(applicationContext, ListOfPetsActivity::class.java)
                startActivity(changeView)
            }
            .addOnFailureListener { e ->
                showAlert("No se ha podido eliminar la mascota.", "Ha ocurrido un fallo inesperado en el servidor.")

                // Para depuración
                Log.w("Error", "Error deleting document", e)
            }
    }
}