package com.example.temp

data class Triangle(val base: Double, val height: Double): Polygon {

    override fun area(): Double {
        return (base * height) / 2
    }

    override fun printArea() {
        println("El área del triángulo es ${area()}")
    }
}
