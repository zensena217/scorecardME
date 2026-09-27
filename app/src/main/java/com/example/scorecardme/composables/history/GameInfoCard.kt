package com.example.scorecardme.composables.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.scorecardme.R
import com.example.scorecardme.data.GameHistory
import com.example.scorecardme.data.Team

@Composable
fun GameInfoCard(modifier: Modifier = Modifier, history: GameHistory) {
    Card(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp, 0.dp,0.dp)) {
            Text("${history.away.name} @ ${history.home.name} - ${history.date}", fontSize = TextUnit(2f, TextUnitType.Em), fontStyle = FontStyle.Italic)
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp, 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
            ) {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(R.mipmap.ic_mia_logo),
                    contentDescription = "${history.away.name} logo"
                )
            }
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text("${history.awayScore} - ${history.homeScore}", fontWeight = FontWeight.Bold)
            }
            Column{
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(R.mipmap.ic_wsh_foreground),
                    contentDescription = "${history.home.name} logo"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewGameInfoCard() {
    GameInfoCard(history = GameHistory(
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
    ))
}