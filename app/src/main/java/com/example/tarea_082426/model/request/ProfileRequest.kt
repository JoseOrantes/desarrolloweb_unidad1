package com.example.tarea_082426.model.request

data class ProfileRequest(
    val fotoBase64: String,
    val telefono: String,
    val correo: String,
    val fechaNac: String,
    val genero: String
)
