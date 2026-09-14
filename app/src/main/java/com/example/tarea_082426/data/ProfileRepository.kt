package com.example.tarea_082426.data

import com.example.tarea_082426.data.remote.RetrofitClient
import com.example.tarea_082426.model.response.ProfileResponse

class ProfileRepository {

    private val apiProfile = RetrofitClient.apiProfile

    suspend fun getProfile(id: Int): Result<ProfileResponse> {
        return try {
            val response = apiProfile.getProfile(id)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Profile not found"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Connection Error: ${e.message}"))
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
    }
}