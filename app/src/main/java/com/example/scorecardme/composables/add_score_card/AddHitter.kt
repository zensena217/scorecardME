package com.example.scorecardme.composables.add_score_card

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.scorecardme.data.DefaultPositions
import com.example.scorecardme.data.Hitter
import com.example.scorecardme.data.Position

@Composable
fun AddHitter(
    order: Int,
    hitters: ArrayList<Hitter>?,
    onAddHitter: (hitter: Hitter) -> Unit
) {
    val rememberHitters = remember { mutableStateListOf<Hitter>().apply {
        if (!hitters.isNullOrEmpty()) {
            addAll(hitters)
        }
    }}
    val hitterName = rememberTextFieldState()
    var hitterPosition by remember {mutableStateOf<Position?>(null) }
    var openDialog by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(4.dp).height(IntrinsicSize.Max).fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().padding(4.dp, 0.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("$order")
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            TextButton(
                onClick = { openDialog = true },
                contentPadding = PaddingValues(8.dp, 0.dp)
            ) {
                Text(if (rememberHitters.isNotEmpty()) {
                    rememberHitters.joinToString("/") {
                        hitter -> "${hitter.name}(${hitter.position.shortLabel})"
                    }
                } else {
                    "Add Player"
                })
            }
        }
    }
    if (openDialog) {
        var error by remember { mutableStateOf(false) }
        Dialog(
            onDismissRequest = { openDialog = false }
        ) {
            Card(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(.33f)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            state = hitterName,
                            label = { Text("Enter Name") },
                            lineLimits = TextFieldLineLimits.SingleLine,
                            isError = error
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Position:")
                        TextButton(
                            onClick = { expanded = true }
                        ) {
                            Text(if (hitterPosition != null) (hitterPosition?.shortLabel ?: "") else "Select Position")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DefaultPositions.entries.filter { it.ordinal != 1 }.forEach { pos ->
                                DropdownMenuItem(
                                    modifier = Modifier.semantics {
                                        contentDescription = pos.position.label
                                    },
                                    text = { Text(pos.position.shortLabel) },
                                    onClick = {
                                        hitterPosition = pos.position
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        ElevatedButton(
                            onClick = {
                                val position = hitterPosition
                                if (hitterName.text.isNotBlank() && position != null) {
                                    val newHitter = Hitter(hitterName.text.toString(), position)
                                    rememberHitters.add(newHitter)
                                    onAddHitter(newHitter)
                                    hitterName.clearText()
                                    openDialog = false
                                } else {
                                    error = true
                                }
                            }
                        ) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddHitter() {
    AddHitter(1, null) { }
}