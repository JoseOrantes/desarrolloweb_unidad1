package com.example.tarea_082426.data

import com.example.tarea_082426.data.remote.RetrofitClient
import com.example.tarea_082426.model.request.RegisterRequest
import com.example.tarea_082426.model.response.Register.RegisterResponse

class RegisterRepository {
    private val apiRegister = RetrofitClient.apiRegister

    suspend fun register(request: RegisterRequest): Result<RegisterResponse> {
        return try {
            val response = apiRegister.register(request)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en el registro"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Connection Error:  ${e.message}"))
        }
    }
}