package com.example.tarea_082426.data.local.dao

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey val userId: Int,
    val fotoBase64: String = "",
    val telefono: String = "",
    val correo: String = "",
    val fechaNac: String = "",
    val genero: String = ""
)