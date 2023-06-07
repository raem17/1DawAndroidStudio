package com.example.petdaycarekotandfire

import java.io.Serializable

data class Pet(var documentID: String, var nombre: String, var raza: String, var peso: Double, var sexo: Sexo, var imageSex: Int): Serializable {
    enum class Sexo {
        Masculino, Femenino
    }
}