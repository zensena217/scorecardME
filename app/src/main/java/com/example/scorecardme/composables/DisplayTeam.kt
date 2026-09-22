package com.example.scorecardme.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.scorecardme.data.Hitter
import com.example.scorecardme.data.Pitcher
import com.example.scorecardme.data.Position
import com.example.scorecardme.data.TeamData

@Composable
fun DisplayTeam(teamData: TeamData) {
    Column(
        Modifier.fillMaxWidth().padding(8.dp)
    ) {
        LazyRow(Modifier.fillMaxWidth()) {
            item {
                Column(
                ) {
                    HorizontalDivider(Modifier.fillParentMaxWidth(1f))
                    Row(
                        modifier = Modifier.height(IntrinsicSize.Min)
                    ) {
                        VerticalDivider()
                        Column(
                            Modifier.padding(6.dp, 0.dp).size(16.dp, 48.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("#", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        VerticalDivider()
                        Column(
                            Modifier.padding(6.dp, 0.dp).size(148.dp, 48.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("Player", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        VerticalDivider()
                        (1..9).forEach {
                            Column(
                                Modifier.size(48.dp, 48.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("$it", fontSize = TextUnit(4f, TextUnitType.Em))
                            }
                            VerticalDivider()
                        }
                    }
                    HorizontalDivider(Modifier.fillParentMaxWidth(1f))
                    teamData.hitters.forEach { (i, hitters) ->
                        Row(
                            modifier = Modifier.height(IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            VerticalDivider()
                            Column(
                                Modifier.padding(6.dp, 0.dp).size(16.dp, 48.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text("$i", fontSize = TextUnit(4f, TextUnitType.Em))
                            }
                            VerticalDivider()
                            Column(
                                Modifier.padding(6.dp, 0.dp).size(148.dp, 48.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(hitters.joinToString("/") { hitter ->
                                    hitter.name
                                }, fontSize = TextUnit(4f, TextUnitType.Em))
                            }
                            VerticalDivider()
                            (1..9).forEach {
                                Column(
                                    Modifier.size(48.dp, 48.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("$it", fontSize = TextUnit(4f, TextUnitType.Em))
                                }
                                VerticalDivider()
                            }
                        }
                        HorizontalDivider(Modifier.fillParentMaxWidth(1f))
                    }
                }
            }
        }
        Row(Modifier.wrapContentSize()) {
            Column(
                Modifier.fillMaxWidth(.75f).padding(0.dp, 24.dp, 0.dp, 4.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                HorizontalDivider()
                Row(
                    Modifier.fillMaxWidth().height(36.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    VerticalDivider()
                    Column(modifier = Modifier.weight(3f).wrapContentHeight()) {
                        Text("Pitcher", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    VerticalDivider()
                    Column(
                        modifier = Modifier.weight(1.25f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("I", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    VerticalDivider()
                    Column(
                        modifier = Modifier.weight(1f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("R", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    VerticalDivider()
                    Column(
                        modifier = Modifier.weight(1f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("ER", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    VerticalDivider()
                    Column(
                        modifier = Modifier.weight(1f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("K", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    VerticalDivider()
                    Column(
                        modifier = Modifier.weight(1.25f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("BB", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    VerticalDivider()
                }
                HorizontalDivider()
                teamData.pitchers.forEach { pitcher ->
                    Row(
                        Modifier.fillMaxWidth().height(48.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        VerticalDivider()
                        Column(
                            modifier = Modifier.weight(3f).wrapContentHeight()
                        ) {
                            Text(
                                pitcher.name,
                                fontSize = TextUnit(4f, TextUnitType.Em),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis)
                        }
                        VerticalDivider()
                        Column(
                            modifier = Modifier.weight(1.25f).wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("${pitcher.inningsPitched}", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        VerticalDivider()
                        Column(
                            modifier = Modifier.weight(1f).wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("${pitcher.runs}", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        VerticalDivider()
                        Column(
                            modifier = Modifier.weight(1f).wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("${pitcher.earnedRuns}", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        VerticalDivider()
                        Column(
                            modifier = Modifier.weight(1f).wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("${pitcher.strikeouts}", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        VerticalDivider()
                        Column(
                            modifier = Modifier.weight(1.25f).wrapContentHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("${pitcher.walks}", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        VerticalDivider()
                    }
                    HorizontalDivider()
                }
                HorizontalDivider()
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