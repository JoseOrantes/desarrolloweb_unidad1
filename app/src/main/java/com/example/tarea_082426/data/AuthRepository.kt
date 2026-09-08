package com.example.tarea_082426.data

class AuthRepository{
    fun login(email: String, password: String): Boolean {
        return email == "admin@email.com" && password == "1234"
        //TODO Agregar backend usando retrofit
    }
}