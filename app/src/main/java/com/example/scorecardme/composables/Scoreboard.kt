package com.example.scorecardme.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.scorecardme.data.ScoreInfo
import com.example.scorecardme.data.ScoreboardData

@Composable
fun Scoreboard(scoreboardData: ScoreboardData) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp, 4.dp)
            .border(1.dp, Color.Black)
    ) {
        Column(
            Modifier
                .weight(1.25f)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = Color.Black,
                        start = Offset(size.width - strokeWidth / 2, 0f),
                        end = Offset(size.width - strokeWidth / 2, size.height),
                        strokeWidth = strokeWidth
                    )
                }
        ) {
            Row {
                Text("", fontSize = TextUnit(4f, TextUnitType.Em))
            }
            Row(
                Modifier.padding(4.dp, 0.dp)
            ) {
                Text(scoreboardData.away.name, fontSize = TextUnit(4f, TextUnitType.Em), maxLines = 1)
            }
            Row(
                Modifier.padding(4.dp, 0.dp)
            ) {
                Text(scoreboardData.home.name, fontSize = TextUnit(4f, TextUnitType.Em), maxLines = 1)
            }
        }

        LazyRow(
            Modifier.weight(3f),
            horizontalArrangement = Arrangement.Start
        ) {
            items(scoreboardData.totalInnings) { index ->
                Column(
                    Modifier
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            drawLine(
                                color = Color.Black,
                                start = Offset(size.width - strokeWidth / 2, 0f),
                                end = Offset(size.width - strokeWidth / 2, size.height),
                                strokeWidth = strokeWidth
                            )
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        Modifier.drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            drawLine(
                                color = Color.Black,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = strokeWidth
                            )
                        }.padding(6.dp, 0.dp)
                    ) {
                        Text("${index + 1}", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    Row(
                        Modifier.drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            drawLine(
                                color = Color.Black,
                                start = Offset(0f, size.height),
                                end = Offset(size.width, size.height),
                                strokeWidth = strokeWidth
                            )
                        }.padding(6.dp, 0.dp)
                    ) {
                        Text("${scoreboardData.away.runs[index]}", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    Row {
                        Text("${scoreboardData.home.runs.getOrNull(index) ?: "x"}", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                }
            }
        }
        Row(
            Modifier.weight(1f)
        ) {
            Column(
                Modifier.padding(4.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text("R", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Row {
                    Text("${scoreboardData.away.runs.reduce{tot, i -> tot + i}}", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Row {
                    Text("${scoreboardData.home.runs.reduce { tot, i -> tot + i }}", fontSize = TextUnit(4f, TextUnitType.Em))
                }
            }
            Column(
                Modifier.padding(4.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text("H", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Row {
                    Text("${scoreboardData.away.hits}", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Row {
                    Text("${scoreboardData.home.hits}", fontSize = TextUnit(4f, TextUnitType.Em))
                }
            }
            Column(
                Modifier.padding(4.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text("E", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Row {
                    Text("${scoreboardData.away.errors}", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Row {
                    Text("${scoreboardData.home.errors}", fontSize = TextUnit(4f, TextUnitType.Em))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScoreboard() {
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
    Scoreboard(scoreboardData)
}