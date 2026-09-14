package com.example.tarea_082426.model.response.Register

import com.example.tarea_082426.model.response.Profile.ProfileBody
import com.example.tarea_082426.model.response.UserResponse

data class RegisterBody(
    val user: UserResponse?,
    val profile: ProfileBody?
)
