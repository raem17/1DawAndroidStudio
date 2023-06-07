package com.example.temp

data class Square(val side: Double): Polygon {

    override fun area(): Double {
        return side * side
    }

    override fun printArea() {
        println("El área del cuadrado es ${area()}")
    }
}