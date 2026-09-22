package com.example.scorecardme.data

data class ScoreboardData(
    val away: ScoreInfo,
    val home: ScoreInfo,
    var totalInnings: Int = away.runs.size
)

data class ScoreInfo(
    val name: String,
    val runs: ArrayList<Int>,
    val hits: Int,
    val errors: Int
)