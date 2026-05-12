package com.example.gramakalyanasports

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MatchDao {

    @Insert
    suspend fun insertMatch(match: MatchEntity)

    @Query("SELECT * FROM matches")
    suspend fun getAllMatches(): List<MatchEntity>

    @Update
    suspend fun updateMatch(match: MatchEntity)
}