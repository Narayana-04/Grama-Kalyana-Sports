package com.example.gramakalyanasports

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.*

@Composable
fun ViewerScreen(
    matchId: String,
    onBack: () -> Unit
) {
    var match by remember { mutableStateOf(Match()) }

    val ref = FirebaseDatabase.getInstance()
        .getReference("matches")
        .child(matchId)

    LaunchedEffect(matchId) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.getValue(Match::class.java)?.let {
                    match = it
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Live Viewer",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("${match.teamA} vs ${match.teamB}")

                Spacer(modifier = Modifier.height(10.dp))

                Text("Score: ${match.scoreA} - ${match.scoreB}")

                if (match.sportType == "Cricket") {
                    Text("Wickets: ${match.wicketsA} - ${match.wicketsB}")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text("Sport: ${match.sportType}")
                Text("Venue: ${match.venue}")
                Text("Status: ${match.status}")

                Spacer(modifier = Modifier.height(10.dp))

                Text("Commentary:")
                Text(match.commentary)

                Spacer(modifier = Modifier.height(10.dp))

                if (match.winner.isNotBlank()) {
                    Text("Winner: ${match.winner}")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}