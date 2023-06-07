package com.example.icecreammaker_ii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var cantidad = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onClickButtonResumen(vista: View) {
        displayAndCalculatePriceInformation()
    }

    fun displayAndCalculatePriceInformation() {

        var textViewPrecioTotal = findViewById<TextView>(R.id.textViewNumPrecioTotal)

        var precioUnidad = 3
        var precioTotal = cantidad * precioUnidad

        textViewPrecioTotal.text = precioTotal.toString()
    }

    fun increase(vista: View) {
        if (cantidad in 1..9) {
            var textViewNumCantidad = findViewById<TextView>(R.id.textViewNumCantidad)

            cantidad++

            textViewNumCantidad.text = cantidad.toString()
        }

        if (cantidad == 10) {
            var alertMax = Toast.makeText(applicationContext, "El número máximo de helados es 10.", Toast.LENGTH_SHORT)
            alertMax.show()
        }
    }

    fun decrease(vista: View) {
        if (cantidad in 2..10) {

            var textViewNumCantidad = findViewById<TextView>(R.id.textViewNumCantidad)

            cantidad--

            textViewNumCantidad.text = cantidad.toString()
        }

        if (cantidad == 1) {
            var alertMin: Toast = Toast.makeText(applicationContext, "El número mínimo de helados es 1.", Toast.LENGTH_SHORT)
            alertMin.show()
        }
    }
}