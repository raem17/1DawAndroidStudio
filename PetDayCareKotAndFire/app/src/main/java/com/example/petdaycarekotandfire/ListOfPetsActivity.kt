package com.example.petdaycarekotandfire

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListOfPetsActivity : AppCompatActivity() {
    private lateinit var floatingActionButtonHuella: FloatingActionButton
    private lateinit var listViewPetList: ListView
    var petsList = arrayListOf<Pet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_pets)

        inicializarVariables()
        setearListeners()
        paintListOfPets()
        listViewPetList_OnItemClickListener()
    }

    private fun inicializarVariables() {
        floatingActionButtonHuella = findViewById(R.id.floatingActionButtonHuella)
        listViewPetList = findViewById(R.id.listViewPetList)
    }

    private fun setearListeners() {
        floatingActionButtonHuella.setOnClickListener {
            val changeView = Intent(applicationContext, AddPetActivity::class.java)
            startActivity(changeView)
        }
    }

    private fun paintListOfPets() {
        val db = Firebase.firestore

        db.collection("pets")
            .get()
            .addOnSuccessListener { result ->
                for (petDoc in result) {
                    val sexo = petDoc.data.get("sexo").toString()

                    if (sexo.equals("Masculino", ignoreCase = true)) {
                        val newPet = Pet(petDoc.id, petDoc.data.get("nombre").toString(), petDoc.data.get("raza").toString(),
                            petDoc.data.get("peso").toString().toDouble(), Pet.Sexo.Masculino, R.drawable.icon_dog_male)

                        petsList.add(newPet)

                    } else if (sexo.equals("Femenino", ignoreCase = true)) {
                        val newPet = Pet(petDoc.id, petDoc.data.get("nombre").toString(), petDoc.data.get("raza").toString(),
                            petDoc.data.get("peso").toString().toDouble(), Pet.Sexo.Femenino, R.drawable.icon_dog_female)

                        petsList.add(newPet)
                    }
                } // Fin de for

                val petArrayAdapter = PetArrayAdapter(applicationContext, R.layout.list_of_pets, petsList)
                listViewPetList.adapter = petArrayAdapter

                listViewPetList.emptyView = findViewById(R.id.textViewEmptyList)

            } // Fin de onSuccess
            .addOnFailureListener { e ->
                showAlert("Error al recuperar los datos de las mascotas.", "Ha ocurrido un fallo inesperado en el servidor.")

                // Para depuración
                Log.w("Error", "$e")
            }
    }

    private fun listViewPetList_OnItemClickListener() {
        listViewPetList.setOnItemClickListener { parent, view, position, l ->
            val selectedPet = petsList[position]

            val changeView = Intent(applicationContext, EditPetActivity::class.java).putExtra("selectedPet", selectedPet)
            startActivity(changeView)
        }
    }

    // Override de botón atrás
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "No se puede volver atrás.", Toast.LENGTH_SHORT).show()
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