package com.example.scorecardme.data

data class ScoreboardData(
    val away: ScoreInfo,
    val home: ScoreInfo,
    var totalInnings: Int = away.runs.size
)

data class ScoreInfo(
    val name: String,
    val runs: ArrayList<Int> = arrayListOf(),
    val hits: Int = 0,
    val errors: Int = 0
)