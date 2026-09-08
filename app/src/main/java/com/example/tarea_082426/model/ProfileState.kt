package com.example.tarea_082426.model

data class ProfileState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val id: Int = 0,
    val userId: Int = 0,
    val nombre: String = "",
    val apellido: String = "",
    val usuario: String = "",
    val fotoBase64: String = "",
    val telefono: String = "",
    val correo: String = "",
    val fechaNac: String = "",
    val genero: String = ""
)
