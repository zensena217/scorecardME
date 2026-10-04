package com.example.scorecardme.data

data class TeamData(
    val hitters: Map<Int, ArrayList<Hitter>>,
    val pitchers: ArrayList<Pitcher>
)

data class Team(
    val name: String,
    val logo: String
)

data class Hitter(
    val name: String,
    val position: Position
)

data class Position(
    val number: Int,
    val label: String,
    val shortLabel: String
)

enum class DefaultPositions(val position: Position) {
    DH(Position(0, "Designated Hitter", "DH")),
    PITCHER(Position(1, "Pitcher", "P")),
    CATCHER(Position(2, "Catcher", "C")),
    FIRST_BASEMAN(Position(3, "First Baseman", "1B")),
    SECOND_BASEMAN(Position(4, "Second Baseman", "2B")),
    THIRD_BASEMAN(Position(5, "Third Baseman", "3B")),
    SHORTSTOP(Position(6, "Shortstop", "SS")),
    LEFT_FIELDER(Position(7, "Left Fielder", "LF")),
    CENTER_FIELDER(Position(8, "Center Fielder", "CF")),
    RIGHT_FIELDER(Position(9, "Right Fielder", "RF"))
}

data class Pitcher(
    val name: String,
    val inningsPitched: Double,
    val strikeouts: Int,
    val walks: Int,
    val runs: Int,
    val earnedRuns: Int,
    val position: Position = DefaultPositions.PITCHER.position
)
