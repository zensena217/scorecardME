package com.example.scorecardme.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scorecardme.data.GameHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HistoryState(
    val currentHistory: ArrayList<GameHistory> = arrayListOf()
)

class HistoryViewModel: ViewModel() {
    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()
}