package com.example.scorecardme.composables.history

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scorecardme.data.GameHistory
import com.example.scorecardme.data.Team


@Composable
fun History(modifier: Modifier = Modifier) {
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
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp, 4.dp)
    ) {
        items(history) { game ->
            Row(modifier = Modifier.padding(0.dp, 4.dp)) {
                GameInfoCard(game)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistory() {
    History()
}