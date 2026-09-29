package com.example.tarea_082426.data.remote

import com.example.tarea_082426.model.request.ProfileRequest
import com.example.tarea_082426.model.response.Profile.ProfileResponse
import com.example.tarea_082426.model.response.StandardResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiProfile {

    @GET("api/users/{id}/profile")
    suspend fun getProfile(@Path("id") id: Int): Response<ProfileResponse>

    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<Map<String, Any>>

    @PUT("api/users/{id}/profile")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body profileRequest: ProfileRequest
    ): Response<StandardResponse>

}