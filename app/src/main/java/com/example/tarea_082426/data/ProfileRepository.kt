package com.example.tarea_082426.data

import android.content.Context
import com.example.tarea_082426.data.local.DatabaseProvider
import com.example.tarea_082426.data.local.entities.User
import com.example.tarea_082426.data.remote.RetrofitClient
import com.example.tarea_082426.model.response.Profile.ProfileResponse

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

    //Si api esta offline
    suspend fun getUserOfflineFirst(context: Context, id: Int): Result<User> {
        val loginDao = DatabaseProvider.getDatabase(context).loginDao()

        // 1. Intentamos buscar en la DB local primero
        val localUser = loginDao.loadById(intArrayOf(id)).firstOrNull()

        if (localUser != null) {
            // ¡Éxito! Tenemos los datos sin usar internet
            return Result.success(localUser)
        }

        // 2. Si no hay nada local, vamos al servidor
        return try {
            val response = apiProfile.getUser(id)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                val userFromServer = User(
                    id_user = id,
                    nombre = data["nombre"]?.toString() ?: "",
                    apellido = data["apellido"]?.toString() ?: "",
                    usuario = data["usuario"]?.toString() ?: "",
                    password = "" // No guardamos password aquí ya que viene del endpoint de detalles, no de login
                )

                // 3. ¡IMPORTANTE! Guardamos en local para la próxima vez
                loginDao.insertAll(userFromServer)

                Result.success(userFromServer)
            } else {
                Result.failure(Exception("Usuario no encontrado en ningún lugar"))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Sin conexión y no hay datos locales"))
        }
    }
}
