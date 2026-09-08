package com.example.tarea_082426.data.remote

import com.example.tarea_082426.model.request.LoginRequest
import com.example.tarea_082426.model.request.RegisterRequest
import com.example.tarea_082426.model.response.LoginResponse
import com.example.tarea_082426.model.response.ProfileResponse
import com.example.tarea_082426.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("api/users/{id}/profile")
    suspend fun getProfile(@Path("id") id: Int): Response<ProfileResponse>

    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<Map<String, Any>>
}
