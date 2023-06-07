package com.example.temp

data class Rectangle(val length: Double, val width: Double): Polygon {

    override fun area(): Double {
        return length * width
    }

    override fun printArea() {
        println("El área del rectángulo es ${area()}")
    }
}
