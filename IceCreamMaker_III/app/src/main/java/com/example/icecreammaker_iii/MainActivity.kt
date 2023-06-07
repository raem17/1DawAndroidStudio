package com.example.icecreammaker_iii

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Variables globales
    var cantidad = 1
    var precioTotal = 0.0

    private lateinit var editTextNombre:EditText
    private lateinit var checkBoxCremaBatida:CheckBox
    private lateinit var checkBoxChocolate: CheckBox
    private lateinit var textViewResumenPedido:TextView
    private lateinit var textViewNumCantidad:TextView
    private lateinit var buttonResumenPedido:Button
    private lateinit var buttonHacerPedido:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNombre = findViewById(R.id.editTextNombre)
        checkBoxCremaBatida = findViewById(R.id.checkBoxCremaBatida)
        checkBoxChocolate = findViewById(R.id.checkBoxChocolate)
        textViewNumCantidad = findViewById(R.id.textViewNumCantidad)
        textViewResumenPedido = findViewById(R.id.textViewResumenPedido)
        buttonResumenPedido = findViewById(R.id.buttonResumenPedido)
        buttonHacerPedido = findViewById(R.id.buttonHacerPedido)

        habilitarBtnResumen()
    }

    // Funciones
    fun onClickButtonResumen(vista: View) {
        calculatePrice()
        displayPriceInformation()
        buttonHacerPedido.isEnabled = true
    }

    fun onClickButtonHacerPedido(vista: View) {
        val correos = arrayOf("lordprofesor@gmail.com")
        composeEmail(correos, "Nuevo pedido", textViewResumenPedido.text.toString())
    }

    fun calculatePrice() {
        var precioUnidad = 3.0
        if (checkBoxCremaBatida.isChecked) {
            precioUnidad += 0.5
        }

        if (checkBoxChocolate.isChecked) {
            precioUnidad += 0.5
        }

        precioTotal = (cantidad * precioUnidad)
    }


    @SuppressLint("SetTextI18n")
    fun displayPriceInformation() {
        val nombreUsuario = editTextNombre.text.toString()
        var tieneCremaBatida = "No"
        var tieneChocolate = "No"

        if (checkBoxCremaBatida.isChecked) {
            tieneCremaBatida = "Sí"
        }

        if (checkBoxChocolate.isChecked) {
            tieneChocolate = "Sí"
        }

        textViewResumenPedido.text = "Nombre: $nombreUsuario\n¿Incluir crema batida?: $tieneCremaBatida\n¿Incluir chocolate?: $tieneChocolate\nCantidad: $cantidad\nTotal: $precioTotal €\n¡Gracias por su compra!"
    }

    fun increase(vista: View) {
        if (cantidad in 1..9) {
            cantidad++

            textViewNumCantidad.text = cantidad.toString()
        }

        if (cantidad == 10) {
            val alertMax = Toast.makeText(applicationContext, "El número máximo de helados es 10.", Toast.LENGTH_SHORT)
            alertMax.show()
        }
    }

    fun decrease(vista: View) {
        if (cantidad in 2..10) {

            cantidad--

            textViewNumCantidad.text = cantidad.toString()
        }

        if (cantidad == 1) {
            val alertMin: Toast = Toast.makeText(applicationContext, "El número mínimo de helados es 1.", Toast.LENGTH_SHORT)
            alertMin.show()
        }
    }

    fun habilitarBtnResumen() {
        // "Caja negra"
        editTextNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.i("beforeTextChanged", "not override")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                buttonResumenPedido.isEnabled = editTextNombre.text.isNotBlank()
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i("afterTextChanged", "not override")
            }

        })
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