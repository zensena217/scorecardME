package com.example.scorecardme.composable.scorecard

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.scorecardme.data.DefaultPositions
import com.example.scorecardme.data.Hitter
import com.example.scorecardme.data.Pitcher
import com.example.scorecardme.data.ScoreInfo
import com.example.scorecardme.data.ScoreboardData
import com.example.scorecardme.data.TeamData

@Composable
fun ScoreCard(modifier: Modifier = Modifier) {
    val scoreboardData = ScoreboardData(
        ScoreInfo(
            "Marlins",
            hashMapOf(),
            arrayListOf(),
            arrayListOf(0,0,0,0,0,0,0,3,1),
            10,
            0
        ),
        ScoreInfo(
            "Nationals",
            hashMapOf(),
            arrayListOf(),
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
            Pair(1, arrayListOf(Hitter("Wood", DefaultPositions.DH.position))),
            Pair(2, arrayListOf(Hitter("Ortiz", DefaultPositions.FIRST_BASEMAN.position))),
            Pair(3, arrayListOf(Hitter("Crews", DefaultPositions.RIGHT_FIELDER.position))),
            Pair(4, arrayListOf(Hitter("Abrams", DefaultPositions.SHORTSTOP.position))),
            Pair(5, arrayListOf(Hitter("House", DefaultPositions.THIRD_BASEMAN.position))),
            Pair(6, arrayListOf(Hitter("Lile", DefaultPositions.LEFT_FIELDER.position))),
            Pair(7, arrayListOf(Hitter("Ford", DefaultPositions.CATCHER.position))),
            Pair(8, arrayListOf(Hitter("Vivas", DefaultPositions.SECOND_BASEMAN.position), Hitter("Nunez",
                DefaultPositions.SECOND_BASEMAN.position))),
            Pair(9, arrayListOf(Hitter("Young", DefaultPositions.CENTER_FIELDER.position))),
        )
    )
    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
    LazyColumn(
        modifier
    ) {
        item {
            Scoreboard(scoreboardData)
        }
        item {
            PrimaryTabRow(
                selectedTabIndex = selectedDestination
            ) {
                Tab(selected = selectedDestination == 0, onClick = {
                    selectedDestination = 0
                }) {
                    Text("Away")
                }
                Tab(selected = selectedDestination == 1, onClick = {
                    selectedDestination = 1
                }) {
                    Text("Home")
                }
            }
            DisplayTeam(teamData)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewScoreCard() {
    ScoreCard()
}