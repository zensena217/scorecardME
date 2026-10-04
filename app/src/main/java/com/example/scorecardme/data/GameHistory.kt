package com.example.scorecardme.data

import kotlinx.serialization.Serializable

@Serializable data class GameHistory(
    val id: Int,
    val home: Team,
    val homeScore: Int,
    val away: Team,
    val awayScore: Int,
    val date: String
)