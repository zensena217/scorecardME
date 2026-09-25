package com.example.scorecardme.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.scorecardme.composables.generic.TableCell
import com.example.scorecardme.data.ScoreInfo
import com.example.scorecardme.data.ScoreboardData

@Composable
fun Scoreboard(scoreboardData: ScoreboardData) {
    val color = MaterialTheme.colorScheme.primaryContainer
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .border(1.dp, color)
    ) {

        TableCell (
            Modifier
                .weight(1.35f)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = color,
                        start = Offset(size.width - strokeWidth / 2, 0f),
                        end = Offset(size.width - strokeWidth / 2, size.height),
                        strokeWidth = strokeWidth
                    )
                },
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text("", fontSize = TextUnit(4f, TextUnitType.Em))
            }
            Row(
                Modifier.padding(4.dp, 0.dp)
            ) {
                Text(scoreboardData.away.name, fontSize = TextUnit(4f, TextUnitType.Em), maxLines = 1)
            }
            HorizontalDivider(color = color)
            Row(
                Modifier.padding(4.dp, 0.dp)
            ) {
                Text(scoreboardData.home.name, fontSize = TextUnit(4f, TextUnitType.Em), maxLines = 1)
            }
        }

        LazyRow(
            Modifier.fillMaxWidth(.5f),
            horizontalArrangement = Arrangement.Start
        ) {
            items(scoreboardData.totalInnings) { index ->
                TableCell(
                    Modifier,
                    drawRight = true,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        Modifier.drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            drawLine(
                                color = color,
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
                                color = color,
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
        Column(
            Modifier.drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = color,
                    start = Offset(size.width - strokeWidth / 2, 0f),
                    end = Offset(size.width - strokeWidth / 2, size.height),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = color,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = color,
                    start = Offset(0f, size.height/3),
                    end = Offset(size.width, size.height/3),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = color,
                    start = Offset(0f, (size.height/1.5).toFloat()),
                    end = Offset(size.width, (size.height/1.5).toFloat()),
                    strokeWidth = strokeWidth
                )
            }.weight(.5f).padding(8.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row{
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
            Modifier.drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = color,
                    start = Offset(size.width - strokeWidth / 2, 0f),
                    end = Offset(size.width - strokeWidth / 2, size.height),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = color,
                    start = Offset(0f, size.height/3),
                    end = Offset(size.width, size.height/3),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = color,
                    start = Offset(0f, (size.height/1.5).toFloat()),
                    end = Offset(size.width, (size.height/1.5).toFloat()),
                    strokeWidth = strokeWidth
                )
            }.weight(.5f).padding(4.dp, 0.dp, 6.dp),
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
            Modifier.weight(.5f).drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = color,
                    start = Offset(0f, size.height / 3),
                    end = Offset(size.width, size.height / 3),
                    strokeWidth = strokeWidth
                )
                drawLine(
                    color = color,
                    start = Offset(0f, (size.height / 1.5).toFloat()),
                    end = Offset(size.width, (size.height / 1.5).toFloat()),
                    strokeWidth = strokeWidth
                )
            }.padding(4.dp, 0.dp),
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