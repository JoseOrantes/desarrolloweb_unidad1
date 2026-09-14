package com.example.tarea_082426.data

import com.example.tarea_082426.data.remote.RetrofitClient
import com.example.tarea_082426.model.request.LoginRequest
//import com.example.tarea_082426.model.request.RegisterRequest
import com.example.tarea_082426.model.response.Login.LoginResponse

//import com.example.tarea_082426.model.response.Register.RegisterResponse

class LoginRepository {

    private val apiLogin = RetrofitClient.apiLogin
    //private val apiRegister = RetrofitClient.apiRegister
    private val apiProfile = RetrofitClient.apiProfile

    suspend fun login(usuario: String, password: String): Result<LoginResponse> {
        return try {
            val response = apiLogin.login(LoginRequest(usuario, password))
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Credenciales incorrectas"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.message}"))
        }
    }

    /*suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return try {
            val response = apiRegister.register(request)
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
            val response = apiProfile.getProfile(id)
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
            val response = apiProfile.getUser(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Usuario no encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de red: ${e.message}"))
        }
    } */
}
