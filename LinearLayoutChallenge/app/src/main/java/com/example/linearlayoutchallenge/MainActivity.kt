package com.example.linearlayoutchallenge

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textView1 = findViewById<TextView>(R.id.guest_1)
        var face : Typeface = Typeface.createFromAsset(assets, "fonts/Louis George Cafe.ttf")
        textView1.typeface = face

        val textView2 = findViewById<TextView>(R.id.guest_2)
        var face2 : Typeface = Typeface.createFromAsset(assets, "fonts/Louis George Cafe.ttf")
        textView2.typeface = face

        val textView3 = findViewById<TextView>(R.id.guest_3)
        var face3: Typeface = Typeface.createFromAsset(assets, "fonts/Louis George Cafe.ttf")
        textView3.typeface = face

        val textView4 = findViewById<TextView>(R.id.guest_4)
        var face4 : Typeface = Typeface.createFromAsset(assets, "fonts/Louis George Cafe.ttf")
        textView4.typeface = face
    }
}