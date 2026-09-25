package com.example.scorecardme.data

data class TeamData(
    val hitters: Map<Int, ArrayList<Hitter>>,
    val pitchers: ArrayList<Pitcher>
)

data class Hitter(
    val name: String,
    val position: Position
)

data class Position(
    val number: Int,
    val name: String
)

data class Pitcher(
    val name: String,
    val inningsPitched: Double,
    val strikeouts: Int,
    val walks: Int,
    val runs: Int,
    val earnedRuns: Int,
    val position: Position = Position(1, "Pitcher")
)
