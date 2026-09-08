package com.example.tarea_082426.model.response

data class ProfileBody(
    val id: Int?,
    val userId: Int?,
    val fotoBase64: String?,
    val telefono: String?,
    val correo: String?,
    val fechaNac: String?,
    val genero: String?
)
