package com.example.scorecardme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scorecardme.data.GameHistory
import com.example.scorecardme.data.Team
import com.example.scorecardme.repository.HistoryRepository
import com.example.scorecardme.store.Games
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HistoryState(
    val currentHistory: List<GameHistory> = listOf(
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

@HiltViewModel
class HistoryViewModel @Inject constructor(
    repository: HistoryRepository
): ViewModel() {
    val history: StateFlow<Games> = repository.getHistory()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Games(arrayListOf())
        )
}