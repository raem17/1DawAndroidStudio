package com.example.firstminiprojectbusiness

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewNameBusiness = findViewById<TextView>(R.id.textViewNameBusiness)
        val face : Typeface = Typeface.createFromAsset(assets, "fonts/BakeryRoast.ttf")
        textViewNameBusiness.typeface = face
    }

    fun mandarEmail(view: View) {
        val correo = arrayOf("therelaxedcoffee@gmail.com")
        composeE    mail(correo)
    }

    fun mostrarPaginaWeb(view: View) {
        openWebPage("https://www.centronelson.org/")
    }

    fun mostrarLugarEnMaps(view: View) {
        showMap("geo:0,0?q=28015+Centro+Nelson".toUri())
    }

    fun hacerLlamada(view: View) {
        dialPhoneNumber("(+34) 646265852")
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun composeEmail(addresses: Array<String>) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, addresses)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun showMap(geoLocation: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}