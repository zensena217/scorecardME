package com.example.scorecardme.composable.add_score_card

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
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scorecardme.R
import com.example.scorecardme.composable.HowToScore
import com.example.scorecardme.composable.scorecard.Scoreboard
import com.example.scorecardme.data.Destination
import com.example.scorecardme.data.ScoreInfo
import com.example.scorecardme.data.ScoreboardData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScoreCard(navController: NavController) {

    var openAddTeamDialog by remember { mutableStateOf(false) }
    var openInfo by remember {mutableStateOf(false)}
    val scoreboardData = remember { mutableStateOf<ScoreboardData?>(null) }
    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
    var baseInnings = 9

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
                    IconButton(
                        onClick = { navController.navigate(Destination.HOME.route) }
                    ) {
                        Icon(
                            modifier = Modifier.size(44.dp),
                            painter = painterResource(R.drawable.ic_back_foreground),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { openInfo = true}
                    ) {
                        Icon(
                            modifier = Modifier.size(44.dp),
                            painter = painterResource(R.drawable.ic_info_foreground),
                            contentDescription = "How to Score"
                        )
                    }
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
                item {
                    PrimaryTabRow(
                        selectedTabIndex = selectedDestination,
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Tab(
                            selected = selectedDestination == 0,
                            onClick = {
                                selectedDestination = 0
                            }) {
                            Text("Away")
                        }
                        Tab(
                            selected = selectedDestination == 1,
                            onClick = {
                                selectedDestination = 1
                            }) {
                            Text("Home")
                        }
                    }
                    when (selectedDestination) {
                        0 -> {
                            (1..9).forEach { order ->
                                AddHitter(order, scoreboardData.value?.away?.hitters?.get(order - 1)) { hitter ->
                                    val current = scoreboardData.value ?: return@AddHitter
                                    val awayHitters = current.away.hitters.toMutableMap().apply {
                                        val hitterList = get(order - 1)?.toMutableList() ?: mutableListOf()
                                        hitterList.add(hitter)
                                        put(order - 1, ArrayList(hitterList))
                                    }
                                    scoreboardData.value = current.copy(
                                        away = current.away.copy(hitters = awayHitters)
                                    )
                                }
                            }
                        }
                        else -> {
                            (1..9).forEach { order ->
                                AddHitter(order, scoreboardData.value?.away?.hitters?.get(order - 1)) { hitter ->
                                    val current = scoreboardData.value ?: return@AddHitter
                                    val homeHitters = current.home.hitters.toMutableMap().apply {
                                        val hitterList = get(order - 1)?.toMutableList() ?: mutableListOf()
                                        hitterList.add(hitter)
                                        put(order - 1, ArrayList(hitterList))
                                    }
                                    scoreboardData.value = current.copy(
                                        home = current.home.copy(hitters = homeHitters)
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                item {
                    Button(
                        onClick = { openAddTeamDialog = true }
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(R.drawable.ic_add_foreground),
                            contentDescription = "Add Teams"
                        )
                        Text("Add Teams")
                    }
                }
            }
        }
        if (openAddTeamDialog) {
            AddTeam(
                onDismiss = {openAddTeamDialog = false},
                onConfirmation = { away, home ->
                    scoreboardData.value = ScoreboardData(ScoreInfo(away), ScoreInfo(home), totalInnings = baseInnings)
                    openAddTeamDialog = false
                }
            )
        }

        if (openInfo) {
            HowToScore(modifier = Modifier.fillMaxSize().padding(it)) { openInfo = false }
        }
    }
}

@Preview
@Composable
fun PreviewAddScoreCard() {
    AddScoreCard(rememberNavController())
}