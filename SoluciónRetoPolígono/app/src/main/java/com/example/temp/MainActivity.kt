package com.example.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*SOLUCION UNO*/
        val miTriangulo = Poligono(10.2f, 5.6f, Poligono.Tipo.TRIANGULO)
        val miCuadrado = Poligono(4.2f, 4.2f, Poligono.Tipo.CUADRADO)
        val miRectangulo = Poligono(12.2f, 6.6f, Poligono.Tipo.RECTANGULO)

        println("Este es el área: ${calcularArea(miTriangulo)}")
        println("Este es el área: ${calcularArea(miCuadrado)}")
        println("Este es el área: ${calcularArea(miRectangulo)}")

        /*SOLUCION DOS*/
        area(Triangle(10.0, 5.0))
        area(Rectangle(5.0, 7.0))
        area(Square(4.0))
    }
}

/*SOLUCION UNO*/

fun calcularArea (p : Poligono) : Float{
    return when (p.tipo){
        Poligono.Tipo.TRIANGULO -> (p.base * p.altura / 2)
        Poligono.Tipo.CUADRADO -> (p.base * p.altura)
        else -> (p.base * p.altura)
    }
}

/*SOLUCION DOS*/
private fun area(polygon: Polygon): Double {
    polygon.printArea()
    return polygon.area()
}
