package com.example.tarea_082426.data.remote

import com.example.tarea_082426.model.request.LoginRequest
import com.example.tarea_082426.model.response.Login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiLogin {

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

}
