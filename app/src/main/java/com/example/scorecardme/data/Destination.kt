package com.example.scorecardme.data

import androidx.compose.ui.graphics.vector.ImageVector

enum class Destination(
    val label: String,
    val contentDescription: String = label,
    val route: String,
    val onMain: Boolean = true
) {
    HOME("Home", route = "home"),
    H2H("H2H", "Head to Head", "h2h"),
    ADD_SCORE_CARD("Add Score Card", route = "add_score_card", onMain = false)
}