package com.example.scorecardme.composable.add_score_card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddTeam(
    onDismiss: () -> Unit,
    onConfirmation: (away: String, home: String) -> Unit
) {
    val away = rememberTextFieldState("")
    val home = rememberTextFieldState("")
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(.33f)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Row {
                    Text("Enter Home & Away Teams")
                }
                Row {
                    OutlinedTextField(
                        state = away,
                        label = { Text("Away")},
                        lineLimits = TextFieldLineLimits.SingleLine
                    )
                }
                Row {
                    OutlinedTextField(
                        state = home,
                        label = { Text("Home")},
                        lineLimits = TextFieldLineLimits.SingleLine
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp, 4.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    ElevatedButton(
                        onClick = {
                            if (away.text.isNotBlank() && home.text.isNotBlank()) {
                                onConfirmation(away.text.toString(), home.text.toString())
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