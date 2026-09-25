package com.example.scorecardme.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.scorecardme.data.Hitter
import com.example.scorecardme.data.Pitcher
import com.example.scorecardme.data.Position
import com.example.scorecardme.data.TeamData
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.style.TextOverflow
import com.example.scorecardme.composables.generic.TableCell

@Composable
fun DisplayTeam(teamData: TeamData) {
    val color = MaterialTheme.colorScheme.primaryContainer
    Column(
        Modifier.fillMaxWidth().padding(8.dp)
    ) {
        LazyRow(Modifier.fillMaxWidth()) {
            item {
                Column(
                    Modifier.border(1.dp, color)
                ) {
                    Row(
                        modifier = Modifier.height(IntrinsicSize.Min)
                    ) {
                        TableCell(
                            Modifier.size(32.dp, 48.dp),
                            drawRight = true
                        ) {
                            Text(
                                text = "#",
                                modifier = Modifier.wrapContentHeight(),
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis)
                        }
                        TableCell(
                            Modifier.padding(6.dp, 0.dp).size(148.dp, 48.dp),
                            horizontalAlignment = Alignment.Start,
                            drawRight = true
                        ) {
                            Text(
                                text = "Player",
                                modifier = Modifier.wrapContentHeight(),
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis)
                        }
                        (1..9).forEach {
                            TableCell(
                                Modifier.size(48.dp, 48.dp),
                                drawRight = true
                            ) {
                                Text(
                                    text = "$it",
                                    modifier = Modifier.wrapContentHeight(),
                                    fontSize = TextUnit(4f, TextUnitType.Em),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis)
                            }
                        }
                    }
                    teamData.hitters.forEach { (i, hitters) ->
                        Row(
                            modifier = Modifier.height(IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TableCell(
                                Modifier.size(32.dp, 48.dp),
                                drawRight = true,
                                drawDown = true
                            ) {
                                Text(
                                    text = "$i",
                                    modifier = Modifier.wrapContentHeight(),
                                    fontSize = TextUnit(4f, TextUnitType.Em),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            TableCell(
                                Modifier.padding(6.dp, 0.dp).size(148.dp, 48.dp),
                                horizontalAlignment = Alignment.Start,
                                drawRight = true,
                                drawDown = true
                            ) {
                                Text(
                                    text = hitters.joinToString("/") { hitter ->
                                        hitter.name
                                    },
                                    modifier = Modifier.wrapContentHeight(),
                                    fontSize = TextUnit(4f, TextUnitType.Em),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            (1..9).forEach {
                                TableCell(
                                    Modifier.size(48.dp, 48.dp),
                                    drawRight = true,
                                    drawDown = true
                                ) {
                                    Text(
                                        text = "$it",
                                        modifier = Modifier.wrapContentHeight(),
                                        fontSize = TextUnit(4f, TextUnitType.Em),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Row(Modifier.wrapContentSize()) {
            Column(
                Modifier
                    .padding(0.dp, 24.dp, 0.dp, 4.dp)
                    .border(1.dp, color)
                    .fillMaxWidth(.75f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    Modifier.fillMaxWidth().height(36.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TableCell(
                        modifier = Modifier.weight(3f).wrapContentHeight().padding(8.dp, 0.dp, 0.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text("Pitcher", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    TableCell(
                        modifier = Modifier.weight(1.25f).wrapContentHeight(),
                        drawLeft = true
                    ) {
                        Text("I", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    TableCell(
                        modifier = Modifier.weight(1.25f).wrapContentHeight(),
                        drawLeft = true
                    ) {
                        Text("R", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    TableCell(
                        modifier = Modifier.weight(1.25f).wrapContentHeight(),
                        drawLeft = true
                    ) {
                        Text("ER", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    TableCell(
                        modifier = Modifier.weight(1.25f).wrapContentHeight(),
                        drawLeft = true
                    ) {
                        Text("K", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    TableCell(
                        modifier = Modifier.weight(1.25f).wrapContentHeight(),
                        drawLeft = true
                    ) {
                        Text("BB", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                }
                teamData.pitchers.forEach { pitcher ->
                    Row(
                        Modifier.drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            drawLine(
                                color = color,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                strokeWidth = strokeWidth
                            )
                        }.height(48.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TableCell(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(3f)
                                .padding(8.dp, 0.dp, 0.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = pitcher.name,
                                modifier = Modifier.wrapContentHeight(),
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        TableCell(
                            modifier = Modifier.fillMaxHeight().weight(1.25f),
                            drawLeft = true
                        ) {
                            Text(
                                text = "${pitcher.inningsPitched}",
                                modifier = Modifier.wrapContentHeight(),
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        TableCell(
                            modifier = Modifier.fillMaxHeight().weight(1.25f),
                            drawLeft = true
                        ) {
                            Text(
                                text = "${pitcher.runs}",
                                modifier = Modifier.wrapContentHeight(),
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        TableCell(
                            modifier = Modifier.fillMaxHeight().weight(1.25f),
                            drawLeft = true
                        ) {
                            Text(
                                text = "${pitcher.earnedRuns}",
                                modifier = Modifier.wrapContentHeight(),
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        TableCell(
                            modifier = Modifier.fillMaxHeight().weight(1.25f),
                            drawLeft = true
                        ) {
                            Text(
                                text = "${pitcher.strikeouts}",
                                modifier = Modifier.wrapContentHeight(),
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        TableCell(
                            modifier = Modifier.fillMaxHeight().weight(1.25f),
                            drawLeft = true
                        ) {
                            Text(
                                text = "${pitcher.walks}",
                                modifier = Modifier.wrapContentHeight(),
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayTeam() {
    val teamData = TeamData(
        pitchers = arrayListOf(
            Pitcher(
                "Alvarez",
                7.0,
                strikeouts = 9,
                walks = 3,
                runs = 0,
                earnedRuns = 0
            ),
            Pitcher(
                "Beeter",
                .2,
                strikeouts = 0,
                walks = 2,
                runs = 3,
                earnedRuns = 3
            ),
            Pitcher(
                name = "Dion",
                inningsPitched = 1.1,
                strikeouts = 2,
                walks = 1,
                runs = 0,
                earnedRuns = 0
            )
        ),
        hitters = mapOf(
            Pair(1, arrayListOf(Hitter("Wood", Position(0, "DH")))),
            Pair(2, arrayListOf(Hitter("Ortiz", Position(3, "1B")))),
            Pair(3, arrayListOf(Hitter("Crews", Position(9, "RF")))),
            Pair(4, arrayListOf(Hitter("Abrams", Position(6, "SS")))),
            Pair(5, arrayListOf(Hitter("House", Position(5, "3B")))),
            Pair(6, arrayListOf(Hitter("Lile", Position(7, "LF")))),
            Pair(7, arrayListOf(Hitter("Ford", Position(2, "C")))),
            Pair(8, arrayListOf(Hitter("Vivas", Position(4, "2B")), Hitter("Nunez", Position(4, "2B")))),
            Pair(9, arrayListOf(Hitter("Young", Position(8, "CF")))),
        )
    )
    DisplayTeam(teamData)
}