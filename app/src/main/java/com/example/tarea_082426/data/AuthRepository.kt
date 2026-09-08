package com.example.tarea_082426.data

import com.example.tarea_082426.data.remote.RetrofitClient
import com.example.tarea_082426.model.request.LoginRequest
import com.example.tarea_082426.model.request.RegisterRequest
import com.example.tarea_082426.model.response.LoginResponse
import com.example.tarea_082426.model.response.ProfileResponse
import com.example.tarea_082426.model.response.RegisterResponse

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

    suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return try {
            val response = apiService.register(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en el registro"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }

    suspend fun getProfile(id: Int): Result<ProfileResponse> {
        return try {
            val response = apiService.getProfile(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Perfil no encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }

    suspend fun getUser(id: Int): Result<Map<String, Any>> {
        return try {
            val response = apiService.getUser(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Usuario no encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de red: ${e.message}"))
        }
    }
}
