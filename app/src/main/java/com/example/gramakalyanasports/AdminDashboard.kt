package com.example.gramakalyanasports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AdminDashboard(
    repository: MatchRepository,
    onOpenHistory: () -> Unit,
    onOpenViewer: () -> Unit,
    onOpenLeaderboard: () -> Unit,
    onBack: () -> Unit
) {
    var matches by remember { mutableStateOf<List<MatchEntity>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            matches = repository.getAllMatches()
        }
    }

    val totalMatches = matches.size
    val liveMatches = matches.count { it.status == "Live" }
    val completedMatches = matches.count { it.status == "Completed" }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Admin Dashboard", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Text("Total Matches: $totalMatches")
        Text("Live Matches: $liveMatches")
        Text("Completed Matches: $completedMatches")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onOpenHistory,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Match History")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onOpenViewer,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Viewer Screen")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onOpenLeaderboard,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Leaderboard")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}