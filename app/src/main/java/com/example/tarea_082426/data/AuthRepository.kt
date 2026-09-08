package com.example.tarea_082426.data

import com.example.tarea_082426.data.remote.RetrofitClient
import com.example.tarea_082426.model.request.LoginRequest
import com.example.tarea_082426.model.response.LoginResponse

/*
class AuthRepository{
    fun login(email: String, password: String): Boolean {
        return email == "admin@email.com" && password == "1234"
        //TODO Agregar backend usando retrofit
    }
}
*/

class AuthRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun login(usuario: String, password: String): Result<LoginResponse> {
        return try {
            val response = apiService.login(LoginRequest(usuario, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Credenciales incorrectas"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }
}
