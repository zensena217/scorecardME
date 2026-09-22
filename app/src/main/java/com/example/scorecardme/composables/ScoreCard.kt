package com.example.scorecardme.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.scorecardme.data.Hitter
import com.example.scorecardme.data.Pitcher
import com.example.scorecardme.data.Position
import com.example.scorecardme.data.ScoreInfo
import com.example.scorecardme.data.ScoreboardData
import com.example.scorecardme.data.TeamData

@Composable
fun ScoreCard(modifier: Modifier = Modifier) {
    val scoreboardData = ScoreboardData(
        ScoreInfo(
            "Marlins",
            arrayListOf(0,0,0,0,0,0,0,3,1),
            10,
            0
        ),
        ScoreInfo(
            "Nationals",
            arrayListOf(3,3,1,0,0,0,0,0),
            15,
            2
        )
    )
    val teamData = TeamData(
        pitchers = arrayListOf(
            Pitcher(
                "Alvarez",
                7.0,
                strikeouts = 9,
                walks = 3,
                runs = 0,
                earnedRuns = 0
            ),
            Pitcher(
                "Beeter",
                .2,
                strikeouts = 0,
                walks = 2,
                runs = 3,
                earnedRuns = 3
            ),
            Pitcher(
                name = "Dion",
                inningsPitched = 1.1,
                strikeouts = 2,
                walks = 1,
                runs = 0,
                earnedRuns = 0
            )
        ),
        hitters = mapOf(
            Pair(1, arrayListOf(Hitter("Wood", Position(0, "DH")))),
            Pair(2, arrayListOf(Hitter("Ortiz", Position(3, "1B")))),
            Pair(3, arrayListOf(Hitter("Crews", Position(9, "RF")))),
            Pair(4, arrayListOf(Hitter("Abrams", Position(6, "SS")))),
            Pair(5, arrayListOf(Hitter("House", Position(5, "3B")))),
            Pair(6, arrayListOf(Hitter("Lile", Position(7, "LF")))),
            Pair(7, arrayListOf(Hitter("Ford", Position(2, "C")))),
            Pair(8, arrayListOf(Hitter("Vivas", Position(4, "2B")), Hitter("Nunez", Position(4, "2B")))),
            Pair(9, arrayListOf(Hitter("Young", Position(8, "CF")))),
        )
    )
    LazyColumn(
        modifier
    ) {
        item {
            Scoreboard(scoreboardData)
        }
        item {
            DisplayTeam(teamData)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewScoreCard() {
    ScoreCard()
}