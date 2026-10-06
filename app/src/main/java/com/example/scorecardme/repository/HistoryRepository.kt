package com.example.scorecardme.repository

import android.content.Context
import com.example.scorecardme.data.GameHistory
import com.example.scorecardme.store.Games
import com.example.scorecardme.store.dataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(private val context: Context) {

    fun getHistory(): Flow<Games> = context.dataStore.data

    suspend fun updateHistory(game: GameHistory) {
        context.dataStore.updateData { games ->
            val newHistory = games.gameHistory
            newHistory.add(game)
            games.copy(gameHistory = newHistory)
        }
    }
}
