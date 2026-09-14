package com.example.tarea_082426.model.response.Login

import com.example.tarea_082426.model.response.StandardResponse

data class LoginResponse(
    val standardResponse: StandardResponse?,
    val body: LoginBody?
)
