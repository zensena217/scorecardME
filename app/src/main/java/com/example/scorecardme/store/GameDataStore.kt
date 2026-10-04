package com.example.scorecardme.store

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.scorecardme.data.GameHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


val Context.dataStore: DataStore<Games> by
dataStore(
    fileName = "settings.json",
    serializer = GamesSerializer,
    scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
)

@Serializable data class Games(val gameHistory: ArrayList<GameHistory>)

object GamesSerializer : Serializer<Games> {
    override val defaultValue: Games = Games(arrayListOf())

    override suspend fun readFrom(input: InputStream): Games =
        try {
            Json.decodeFromString(Games.serializer(), input.readBytes().decodeToString())
        } catch(error: SerializationException) {
            throw CorruptionException("Unable to read", error)
        }

    override suspend fun writeTo(t: Games, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(Json.encodeToString(Games.serializer(), t).encodeToByteArray())
        }
    }
}

class GameDataStore(private val context: Context) {
    fun gamesHistoryFlow(): Flow<ArrayList<GameHistory>> = context.dataStore.data.map { games -> games.gameHistory }

    suspend fun updateGames(game: GameHistory) {
        context.dataStore.updateData { games ->
            val history = games.gameHistory
            history.add(game)
            games.copy(gameHistory = history)
        }
    }
}