package com.example.petdaycarekotandfire

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var buttonAcceder: Button
    private lateinit var buttonRegistrarse: Button
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarVariables()
        setearListeners()
        cambiarDatoCurioso()
    }

    private fun inicializarVariables() {
        buttonAcceder = findViewById(R.id.buttonAcceder)
        buttonRegistrarse = findViewById(R.id.buttonRegistrarse)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
    }

    private fun setearListeners() {
        buttonRegistrarse.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isNotBlank() && password.isNotBlank()) {
                register(email, password)

            } else {
                val message = Toast.makeText(applicationContext, "Introduzca correctamente los datos.", Toast.LENGTH_LONG)
                message.show()
            }
        }

        buttonAcceder.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isNotBlank() && password.isNotBlank()) {
                login(email, password)

            } else {
                val message = Toast.makeText(applicationContext, "Introduzca correctamente los datos.", Toast.LENGTH_LONG)
                message.show()
            }
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

    private fun register (email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            if (it.isSuccessful) {
                val message = Toast.makeText(applicationContext, "Se ha registrado con éxito.", Toast.LENGTH_LONG)
                message.show()

                login(email, password)

            } else {
                showAlert("No se ha podido realizar el registro.", "Ha ocurrido un fallo inesperado en el servidor.")
            }
        }
    }
    private fun login (email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if (it.isSuccessful) {
                val changeView = Intent(applicationContext, ListOfPetsActivity::class.java)
                startActivity(changeView)

            } else {
                showAlert("No se ha podido iniciar sesión.", "Ha ocurrido un fallo inesperado en el servidor.")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun cambiarDatoCurioso() {
        val textViewDatoCurioso = findViewById<TextView>(R.id.textViewDatoCurioso)

        when (Random.nextInt(1, 11)) {
            1 -> textViewDatoCurioso.text = "Los perros tienen aproximadamente 300 millones de receptores de olor en comparación con los 6 millones que tienen los humanos."
            2 -> textViewDatoCurioso.text = "Los perros pueden entender hasta unas 250 palabras y gestos humanos."
            3 -> textViewDatoCurioso.text = "El récord mundial del perro más alto registrado fue un gran danés llamado Zeus, que medía 1.12 metros de altura desde la pata hasta el hombro."
            4 -> textViewDatoCurioso.text = "El perro más pequeño registrado es un chihuahua llamado Miracle Milly, que mide solo 9.65 centímetros de altura."
            5 -> textViewDatoCurioso.text = "Los perros tienen un sentido del oído cuatro veces más agudo que el de los humanos."
            6 -> textViewDatoCurioso.text = "Algunas razas de perros, como el basenji, no ladran. En su lugar, emiten un sonido único llamado \"ululato\"."
            7 -> textViewDatoCurioso.text = "Los perros tienen una capacidad de visión nocturna superior a la de los humanos debido a una estructura especial en sus ojos llamada \"tapetum lucidum\"."
            8-> textViewDatoCurioso.text = "Los perros sudan a través de las almohadillas de sus patas y regulan principalmente su temperatura a través de la respiración."
            9 -> textViewDatoCurioso.text = "Se cree que los perros tienen una edad promedio de 7 años por cada año humano, pero este cálculo varía según la raza y el tamaño."
            10 -> textViewDatoCurioso.text = "Los perros pueden detectar enfermedades como el cáncer y la diabetes mediante su agudo sentido del olfato."
        }
    }
}