package com.example.tarea_082426.model.request

data class RegisterRequest(
    val nombre: String,
    val apellido: String,
    val usuario: String,
    val password: String,
    val fotoBase64: String,
    val telefono: String,
    val correo: String,
    val fechaNac: String,
    val genero: String
)
