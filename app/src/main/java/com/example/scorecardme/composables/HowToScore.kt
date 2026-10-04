package com.example.scorecardme.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.example.scorecardme.R
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HowToScore(
    modifier: Modifier = Modifier,
    close: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(true)
    val resources = LocalResources.current

    ModalBottomSheet(
        onDismissRequest = {close()},
        modifier = modifier,
        sheetState = sheetState
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp, 0.dp, 16.dp),
            text = stringResource(R.string.how_to_score),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(5f, TextUnitType.Em)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.how_to_start)
                    )
                }
            }
            item {
                Row {
                    Text(
                        text = stringResource(R.string.position_numbers)
                    )
                }
                Row {
                    Text(
                        text = buildAnnotatedString {
                            withBulletList {
                                resources.getStringArray(R.array.positions).forEach { item ->
                                    withBulletListItem { append(item) }
                                }
                            }
                        }
                    )
                }
            }
            item {
                Row {
                    Text(stringResource(R.string.scoring_example_title))
                }
                Row {
                    Text(text = buildAnnotatedString {
                        withBulletList {
                            resources.getStringArray(R.array.scoring_examples).forEach { item ->
                                withBulletListItem { append(item) }
                            }
                        }
                    })
                }
            }
            item {
                Row {
                    Text(stringResource(R.string.scoring_credit))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHowToScore() {
    HowToScore { }
}