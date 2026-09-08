package com.example.tarea_082426.data.remote

import com.example.tarea_082426.model.request.LoginRequest
import com.example.tarea_082426.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
