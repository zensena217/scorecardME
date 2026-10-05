package com.example.scorecardme.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scorecardme.R
import com.example.scorecardme.composable.history.History
import com.example.scorecardme.composable.add_score_card.AddScoreCard
import com.example.scorecardme.composable.scorecard.ScoreCard
import com.example.scorecardme.data.Destination
import com.example.scorecardme.viewmodel.HistoryViewModel

@Composable
fun AppHost(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val startDestination = Destination.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { NavigationBar {
            Destination.entries.filter{ it.onMain }.forEachIndexed { index, destination ->
                NavigationBarItem(
                    selected = selectedDestination == index,
                    onClick = {
                        navController.navigate(route = destination.route)
                        selectedDestination = index
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(44.dp),
                            painter = painterResource(when(index) {
                                1 -> R.drawable.ic_h2h_foreground
                                else -> R.drawable.ic_home_foreground
                            }),
                            contentDescription = destination.contentDescription
                        )
                    },
                    label = {Text(destination.label)}
                )
            }
        } }
    ) { innerPadding ->
        val modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .padding(8.dp)
        NavHost(navController, startDestination = startDestination.route) {
            Destination.entries.forEach { destination ->
                composable(destination.route) {
                    when (destination) {
                        Destination.HOME -> History(modifier, navController, state.currentHistory)
                        Destination.H2H -> ScoreCard(modifier)
                        Destination.ADD_SCORE_CARD -> AddScoreCard(navController)
                    }
                }
            }
        }
    }
}