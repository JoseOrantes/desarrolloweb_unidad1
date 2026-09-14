package com.example.tarea_082426.model.response.Login

import com.example.tarea_082426.model.response.UserResponse

data class LoginBody(
    val token: String?,
    val user: UserResponse?
)
