package com.example.tarea_082426.data.remote

import com.example.tarea_082426.model.response.Profile.ProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiProfile {

    @GET("api/users/{id}/profile")
    suspend fun getProfile(@Path("id") id: Int): Response<ProfileResponse>

    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<Map<String, Any>>

}