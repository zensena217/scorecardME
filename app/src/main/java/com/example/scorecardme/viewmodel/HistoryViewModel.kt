package com.example.scorecardme.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scorecardme.data.GameHistory
import com.example.scorecardme.data.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HistoryState(
    val currentHistory: ArrayList<GameHistory> = arrayListOf(
        GameHistory(
            0,
            Team(
                "WSH",
                ""
            ),
            8,
            Team(
                "MIA",
                ""
            ),
            3,
            "9/12/26"
        ),
        GameHistory(
            0,
            Team(
                "WSH",
                ""
            ),
            2,
            Team(
                "MIA",
                ""
            ),
            5,
            "9/13/26"
        )
    )
)

class HistoryViewModel: ViewModel() {
    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()
}