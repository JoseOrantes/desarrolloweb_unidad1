package com.example.tarea_082426.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profiles")
    suspend fun getAll(): List<Profile>

    @Query("SELECT * FROM profiles WHERE userId = :userId LIMIT 1")
    suspend fun loadById(userId: Int): Profile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(profile: Profile)
}