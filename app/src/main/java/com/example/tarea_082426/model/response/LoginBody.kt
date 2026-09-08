package com.example.tarea_082426.model.response

data class LoginBody(
    val token: String?,
    val user: UserResponse?
)
