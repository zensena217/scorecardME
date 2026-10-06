package com.example.scorecardme.composable.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scorecardme.R
import com.example.scorecardme.data.GameHistory
import com.example.scorecardme.data.Team


@Composable
fun History(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    history: List<GameHistory>
) {
    val colors = MaterialTheme.colorScheme
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "Past Games",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = colors.tertiary,
            fontSize = TextUnit(6f, TextUnitType.Em)
        )
        if (history.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.align(Alignment.TopCenter),
                contentPadding = PaddingValues(8.dp, 4.dp),
                overscrollEffect = null
            ) {
                items(history) { game ->
                    Row(modifier = Modifier.padding(0.dp, 4.dp)) {
                        GameInfoCard(Modifier.fillMaxWidth().fillMaxHeight(.33f), game)
                    }
                }
            }
        } else {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.no_game_history),
                textAlign = TextAlign.Center
            )
        }

        FloatingActionButton (
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            onClick = {
                navController.navigate("add_score_card")
            },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(R.drawable.baseline_post_add_24),
                contentDescription = "Add Game",
                tint = colors.tertiary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistory() {
    val history = arrayListOf(
        GameHistory(
            0,
            Team(
                "WSH",
                ""
            ),
            8,
            Team(
                "MIA",
                ""
            ),
            3,
            "9/12/26"
        ),
        GameHistory(
            0,
            Team(
                "WSH",
                ""
            ),
            2,
            Team(
                "MIA",
                ""
            ),
            5,
            "9/13/26"
        )
    )
    History(navController = rememberNavController(), history = arrayListOf())
}