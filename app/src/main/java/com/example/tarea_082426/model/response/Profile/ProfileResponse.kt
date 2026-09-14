package com.example.tarea_082426.model.response.Profile

import com.example.tarea_082426.model.response.StandardResponse

data class ProfileResponse(
    val standardResponse: StandardResponse?,
    val body: ProfileBody?
)
