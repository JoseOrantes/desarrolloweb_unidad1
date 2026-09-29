package com.example.tarea_082426.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.example.tarea_082426.data.local.entities.User

@Dao
interface LoginDao {
    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE id_user IN (:userIds)")
    suspend fun loadById(userIds: IntArray): List<User>

    // @Query("SELECT * FROM users WHERE usuario = :u AND password = :p LIMIT 1")
    @Query("SELECT * FROM users WHERE (usuario = :u OR email = :u) AND password = :p LIMIT 1")
    suspend fun checkOfflineLogin(u: String, p: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: User)
}
