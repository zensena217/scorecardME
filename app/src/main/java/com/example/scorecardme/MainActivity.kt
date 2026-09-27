package com.example.scorecardme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scorecardme.composables.ScoreCard
import com.example.scorecardme.composables.history.History
import com.example.scorecardme.data.Destination
import com.example.scorecardme.ui.theme.ScoreCardMETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val startDestination = Destination.HOME
            var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
            ScoreCardMETheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { NavigationBar {
                        Destination.entries.forEachIndexed { index, destination ->
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
                                    Destination.HOME -> History(modifier)
                                    Destination.H2H -> ScoreCard(modifier)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}