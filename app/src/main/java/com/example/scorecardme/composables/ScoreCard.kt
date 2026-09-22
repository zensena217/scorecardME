package com.example.scorecardme.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.scorecardme.data.ScoreInfo
import com.example.scorecardme.data.ScoreboardData

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
    LazyColumn(
        modifier
    ) {
        item {
            Scoreboard(scoreboardData)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewScoreCard() {
    ScoreCard()
}