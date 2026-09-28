package com.example.scorecardme.composables.add_score_card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scorecardme.R
import com.example.scorecardme.composables.scorecard.Scoreboard
import com.example.scorecardme.data.Hitter
import com.example.scorecardme.data.Pitcher
import com.example.scorecardme.data.ScoreInfo
import com.example.scorecardme.data.ScoreboardData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScoreCard(navController: NavController) {

    val openAddTeamDialog = remember { mutableStateOf(false) }
    val scoreboardData = remember { mutableStateOf<ScoreboardData?>(null) }

    var baseInnings = 9

    var awayTeam: ScoreInfo? = null
    var awayHitters = arrayListOf<Hitter>()
    var awayPitchers = arrayListOf<Pitcher>()

    var homeTeam: ScoreInfo? = null
    var homeHitters = arrayListOf<Hitter>()
    var homePitchers = arrayListOf<Pitcher>()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("New Score Card")
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.size(44.dp),
                        painter = painterResource(R.drawable.ic_home_foreground),
                        contentDescription = "Back"
                    )
                }
            )
        }

    ) {
        LazyColumn(
            modifier = Modifier.padding(it).fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (scoreboardData.value != null) {
                item {
                    Scoreboard(scoreboardData.value!!)
                }
            } else {
                item {
                    Button(
                        onClick = { openAddTeamDialog.value = true }
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(R.drawable.baseline_post_add_24),
                            contentDescription = "Add Teams"
                        )
                        Text("Add Teams")
                    }
                }
            }
        }
        if (openAddTeamDialog.value) {
            AddTeam(
                onDismiss = {openAddTeamDialog.value = false},
                onConfirmation = { away, home ->
                    awayTeam = ScoreInfo(away)
                    homeTeam = ScoreInfo(home)
                    scoreboardData.value = ScoreboardData(awayTeam, homeTeam, totalInnings = baseInnings)
                    openAddTeamDialog.value = false
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewAddScoreCard() {
    AddScoreCard(rememberNavController())
}