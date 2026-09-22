package com.example.scorecardme.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
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
import java.util.Objects

@Composable
fun DisplayTeam(teamData: TeamData) {
    Column(
        Modifier.fillMaxWidth().padding(8.dp)
    ) {
        LazyRow(Modifier.fillMaxWidth()) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Column(
                            Modifier.width(12.dp)
                        ) {
                            Text("#", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        Column(
                            Modifier.width(144.dp)
                        ) {
                            Text("Player", fontSize = TextUnit(4f, TextUnitType.Em))
                        }
                        (1..9).forEach {
                            Column(
                                Modifier.width(24.dp)
                            ) {
                                Text("$it", fontSize = TextUnit(4f, TextUnitType.Em))
                            }
                        }
                    }
                    teamData.hitters.forEach { (i, hitters) ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Column(
                                Modifier.width(12.dp)
                            ) {
                                Text("$i", fontSize = TextUnit(4f, TextUnitType.Em))
                            }
                            Column(
                                Modifier.width(144.dp)
                            ) {
                                Text(hitters.joinToString("/") { hitter ->
                                    hitter.name
                                }, fontSize = TextUnit(4f, TextUnitType.Em))
                            }
                            (1..9).forEach {
                                Column(
                                    Modifier.width(24.dp)
                                ) {
                                    Text("$it", fontSize = TextUnit(4f, TextUnitType.Em))
                                }
                            }
                        }
                    }
                }
            }
        }
        Column(
            Modifier.fillMaxWidth(.66f).padding(0.dp, 24.dp, 0.dp, 4.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(modifier = Modifier.weight(3f).wrapContentHeight()) {
                    Text("Pitcher", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Column(
                    modifier = Modifier.weight(1.5f).wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("I", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Column(
                    modifier = Modifier.weight(1f).wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("R", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Column(
                    modifier = Modifier.weight(1f).wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("ER", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Column(
                    modifier = Modifier.weight(1f).wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("K", fontSize = TextUnit(4f, TextUnitType.Em))
                }
                Column(
                    modifier = Modifier.weight(1f).wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("BB", fontSize = TextUnit(4f, TextUnitType.Em))
                }
            }
            teamData.pitchers.forEach { pitcher ->
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(3f).wrapContentHeight()
                    ) {
                        Text(pitcher.name, fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    Column(
                        modifier = Modifier.weight(1.5f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("${pitcher.inningsPitched}", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    Column(
                        modifier = Modifier.weight(1f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("${pitcher.runs}", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    Column(
                        modifier = Modifier.weight(1f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("${pitcher.earnedRuns}", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    Column(
                        modifier = Modifier.weight(1f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("${pitcher.strikeouts}", fontSize = TextUnit(4f, TextUnitType.Em))
                    }
                    Column(
                        modifier = Modifier.weight(1f).wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("${pitcher.walks}", fontSize = TextUnit(4f, TextUnitType.Em))
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