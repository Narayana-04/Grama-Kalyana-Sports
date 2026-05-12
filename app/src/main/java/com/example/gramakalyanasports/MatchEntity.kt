package com.example.gramakalyanasports

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val teamA: String,
    val teamB: String,
    val sportType: String,
    val venue: String,
    val matchDate: String,
    val matchTime: String,

    val scoreA: Int = 0,
    val scoreB: Int = 0,

    val wicketsA: Int = 0,
    val wicketsB: Int = 0,

    val commentary: String = "",

    val winner: String = "",

    val status: String = "Upcoming"
)