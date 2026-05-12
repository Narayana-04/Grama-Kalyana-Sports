package com.example.gramakalyanasports

data class Match(
    val matchId: String = "",

    val teamA: String = "",
    val teamB: String = "",

    val teamALeader: String = "",
    val teamBLeader: String = "",

    val teamAMembers: List<String> = emptyList(),
    val teamBMembers: List<String> = emptyList(),

    // Common score
    val scoreA: Int = 0,
    val scoreB: Int = 0,

    // Cricket
    val wicketsA: Int = 0,
    val wicketsB: Int = 0,

    // Volleyball sets
    val setsA: Int = 0,
    val setsB: Int = 0,

    val sportType: String = "Cricket",

    val venue: String = "",
    val matchDate: String = "",
    val matchTime: String = "",

    val commentary: String = "",
    val winner: String = "",
    val status: String = "Upcoming"
)