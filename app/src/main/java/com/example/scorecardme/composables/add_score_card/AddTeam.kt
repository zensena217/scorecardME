package com.example.scorecardme.composables.add_score_card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddTeam(
    onDismiss: () -> Unit,
    onConfirmation: (away: String, home: String) -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val away = rememberTextFieldState("")
    val home = rememberTextFieldState("")
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxSize(.8f)
            ) {
                Row {
                    Text("Enter Home & Away Teams")
                }
                Row {
                    OutlinedTextField(
                        state = away,
                        label = { Text("Away")}
                    )
                }
                Row {
                    OutlinedTextField(
                        state = home,
                        label = { Text("Home")}
                    )
                }
                Row {
                    ElevatedButton (
                        onClick = {onDismiss()},
                        colors = ButtonColors(colors.secondaryContainer, colors.secondary, Color.Gray, colors.secondary)
                    ) {
                        Text("Cancel")
                    }
                    ElevatedButton(
                        onClick = {
                            if (away.text.isNotBlank() && home.text.isNotBlank()) {
                                onConfirmation(away.text.toString(), home.text.toString())
                            }
                        },
                        colors = ButtonColors(Color.Green, colors.primary, Color.Gray, colors.primary)
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}