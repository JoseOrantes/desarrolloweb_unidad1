package com.example.tarea_082426.model

data class LoginState(
    val email: String = "",
    val password: String = "",
    val mensaje: String = "",
    val loginExitoso: Boolean = false
)