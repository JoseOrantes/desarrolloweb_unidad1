package com.example.tarea_082426.model.response.Register

import com.example.tarea_082426.model.response.StandardResponse

data class RegisterResponse(
    val standardResponse: StandardResponse?,
    val body: RegisterBody?
)
