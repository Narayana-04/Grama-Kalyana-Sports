package com.example.gramakalyanasports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class LeaderboardItem(
    val teamName: String,
    val wins: Int,
    val totalScore: Int
)

@Composable
fun LeaderboardScreen(
    repository: MatchRepository,
    onBack: () -> Unit
) {
    var matches by remember { mutableStateOf<List<MatchEntity>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            matches = repository.getAllMatches()
        }
    }

    val leaderboard = remember(matches) {
        val stats = mutableMapOf<String, Pair<Int, Int>>()

        matches.forEach { match ->
            listOf(match.teamA, match.teamB).forEach { team ->
                if (team.isNotBlank()) {
                    val current = stats[team] ?: (0 to 0)

                    val addedScore =
                        if (team == match.teamA) match.scoreA else match.scoreB

                    val addedWin =
                        if (match.winner == team) 1 else 0

                    stats[team] =
                        (current.first + addedWin) to
                                (current.second + addedScore)
                }
            }
        }

        stats.map {
            LeaderboardItem(
                teamName = it.key,
                wins = it.value.first,
                totalScore = it.value.second
            )
        }.sortedByDescending { it.wins }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Leaderboard", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(leaderboard) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(item.teamName)
                        Text("Wins: ${item.wins}")
                        Text("Total Score: ${item.totalScore}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}