package com.example.scorecardme.data

import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val label: String,
    val contentDescription: String = label,
    val route: String
) {
    HOME("Home", route = "home"),
    H2H("H2H", "Head to Head", "h2h")
}