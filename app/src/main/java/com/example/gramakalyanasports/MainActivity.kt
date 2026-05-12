package com.example.gramakalyanasports

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar
import java.util.UUID
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
class MainActivity : ComponentActivity() {

    private val db = FirebaseDatabase.getInstance().getReference("matches")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppScreen()
                }
            }
        }
    }

    @Composable
    fun AppScreen() {
        var selectedMatch by remember { mutableStateOf<Match?>(null) }
        var showCreateScreen by remember { mutableStateOf(false) }

        when {
            showCreateScreen -> {
                CreateMatchScreen {
                    showCreateScreen = false
                }
            }

            selectedMatch != null -> {
                ScoreScreen(
                    match = selectedMatch!!,
                    onBack = { selectedMatch = null }
                )
            }

            else -> {
                MatchListScreen(
                    onCreate = { showCreateScreen = true },
                    onOpen = { selectedMatch = it }
                )
            }
        }
    }

    @Composable
    fun MatchListScreen(
        onCreate: () -> Unit,
        onOpen: (Match) -> Unit
    ) {
        var matches by remember { mutableStateOf(listOf<Match>()) }

        LaunchedEffect(Unit) {
            db.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
                override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                    matches = snapshot.children.mapNotNull {
                        it.getValue(Match::class.java)
                    }
                }

                override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                }
            })
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Grama-Kalyana Sports",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onCreate,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Match")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (matches.isEmpty()) {
                Text("No matches created yet")
            } else {
                LazyColumn {
                    items(matches) { match ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable { onOpen(match) }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    "${match.teamA} vs ${match.teamB}",
                                    fontWeight = FontWeight.Bold
                                )
                                Text("Score: ${match.scoreA} - ${match.scoreB}")
                                Text("Sport: ${match.sportType}")
                                Text("Status: ${match.status}")
                                Text("Venue: ${match.venue}")
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun CreateMatchScreen(onDone: () -> Unit) {
        var teamA by remember { mutableStateOf("") }
        var teamB by remember { mutableStateOf("") }
        var leaderA by remember { mutableStateOf("") }
        var leaderB by remember { mutableStateOf("") }
        var venue by remember { mutableStateOf("") }
        var date by remember { mutableStateOf("") }
        var time by remember { mutableStateOf("") }
        var sport by remember { mutableStateOf("Cricket") }

        var membersA by remember { mutableStateOf(listOf<String>()) }
        var membersB by remember { mutableStateOf(listOf<String>()) }

        var newPlayerA by remember { mutableStateOf("") }
        var newPlayerB by remember { mutableStateOf("") }

        val maxPlayers = if (sport == "Cricket") 11 else 12
        val calendar = Calendar.getInstance()
        val context = this

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("Create Match", fontSize = 22.sp)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = teamA,
                onValueChange = { teamA = it },
                label = { Text("Team A") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = teamB,
                onValueChange = { teamB = it },
                label = { Text("Team B") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = leaderA,
                onValueChange = { leaderA = it },
                label = { Text("Team A Leader") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = leaderB,
                onValueChange = { leaderB = it },
                label = { Text("Team B Leader") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = venue,
                onValueChange = { venue = it },
                label = { Text("Venue") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Button(
                    onClick = {
                        DatePickerDialog(
                            context,
                            { _, year, month, day ->
                                date = "$day/${month + 1}/$year"
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }
                ) {
                    Text(if (date.isBlank()) "Pick Date" else date)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        TimePickerDialog(
                            context,
                            { _, hour, minute ->
                                time = String.format("%02d:%02d", hour, minute)
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                        ).show()
                    }
                ) {
                    Text(if (time.isBlank()) "Pick Time" else time)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            listOf("Cricket", "Kabaddi", "Volleyball").forEach { game ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = sport == game,
                        onClick = { sport = game }
                    )
                    Text(game)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("Team A Players (${membersA.size}/$maxPlayers)")

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newPlayerA,
                    onValueChange = { newPlayerA = it },
                    label = { Text("Player Name") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (newPlayerA.isNotBlank() && membersA.size < maxPlayers) {
                            membersA = membersA + newPlayerA
                            newPlayerA = ""
                        }
                    }
                ) {
                    Text("+")
                }
            }

            Text(membersA.joinToString(", "))

            Spacer(modifier = Modifier.height(10.dp))

            Text("Team B Players (${membersB.size}/$maxPlayers)")

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newPlayerB,
                    onValueChange = { newPlayerB = it },
                    label = { Text("Player Name") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (newPlayerB.isNotBlank() && membersB.size < maxPlayers) {
                            membersB = membersB + newPlayerB
                            newPlayerB = ""
                        }
                    }
                ) {
                    Text("+")
                }
            }

            Text(membersB.joinToString(", "))

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (
                        teamA.isBlank() ||
                        teamB.isBlank() ||
                        leaderA.isBlank() ||
                        leaderB.isBlank() ||
                        venue.isBlank() ||
                        date.isBlank() ||
                        time.isBlank() ||
                        membersA.size != maxPlayers ||
                        membersB.size != maxPlayers
                    ) {
                        Toast.makeText(
                            context,
                            "Fill all details correctly",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }

                    val matchId = UUID.randomUUID().toString()

                    val match = Match(
                        matchId = matchId,
                        teamA = teamA,
                        teamB = teamB,
                        teamALeader = leaderA,
                        teamBLeader = leaderB,
                        teamAMembers = membersA,
                        teamBMembers = membersB,
                        sportType = sport,
                        venue = venue,
                        matchDate = date,
                        matchTime = time,
                        status = "Live"
                    )

                    db.child(matchId).setValue(match)
                    onDone()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Match")
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }

    @Composable
    fun ScoreScreen(
        match: Match,
        onBack: () -> Unit
    ) {
        var scoreA by remember { mutableStateOf(match.scoreA) }
        var scoreB by remember { mutableStateOf(match.scoreB) }
        var wicketsA by remember { mutableStateOf(match.wicketsA) }
        var wicketsB by remember { mutableStateOf(match.wicketsB) }
        var setsA by remember { mutableStateOf(match.setsA) }
        var setsB by remember { mutableStateOf(match.setsB) }
        var commentary by remember { mutableStateOf(match.commentary) }
        var winner by remember { mutableStateOf(match.winner) }

        val backgroundImage = when (match.sportType) {
            "Cricket" -> R.drawable.cricket_bg
            "Kabaddi" -> R.drawable.kabaddi_bg
            "Volleyball" -> R.drawable.volleyball_bg
            else -> R.drawable.cricket_bg
        }

        fun saveMatch(status: String = "Live") {
            val updated = match.copy(
                scoreA = scoreA,
                scoreB = scoreB,
                wicketsA = wicketsA,
                wicketsB = wicketsB,
                setsA = setsA,
                setsB = setsB,
                commentary = commentary,
                winner = winner,
                status = status
            )
            db.child(match.matchId).setValue(updated)
        }

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.60f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    "${match.teamA} vs ${match.teamB}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFF8E1)
                )

                Text(
                    "${match.sportType} | ${match.venue}",
                    color = Color(0xFFFFF8E1)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "Score: $scoreA - $scoreB",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Cyan
                )

                if (match.sportType == "Cricket") {
                    Text(
                        "Wickets: $wicketsA - $wicketsB",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

                if (match.sportType == "Volleyball") {
                    Text(
                        "Sets: $setsA - $setsB",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "Commentary: $commentary",
                    color = Color(0xFFFFEB3B),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                when (match.sportType) {

                    "Cricket" -> {
                        Text(
                            "Cricket Controls",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            listOf(1, 2, 3, 4, 6).forEach { points ->
                                Button(
                                    onClick = {
                                        scoreA += points
                                        commentary = "${match.teamA} scored $points runs"
                                        winner = if (scoreA > scoreB) match.teamA else match.teamB
                                        saveMatch()
                                    },
                                    modifier = Modifier.padding(2.dp)
                                ) {
                                    Text("+$points A")
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            listOf(1, 2, 3, 4, 6).forEach { points ->
                                Button(
                                    onClick = {
                                        scoreB += points
                                        commentary = "${match.teamB} scored $points runs"
                                        winner = if (scoreB > scoreA) match.teamB else match.teamA
                                        saveMatch()
                                    },
                                    modifier = Modifier.padding(2.dp)
                                ) {
                                    Text("+$points B")
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(onClick = {
                                wicketsA++
                                commentary = "${match.teamA} lost wicket"
                                saveMatch()
                            }) {
                                Text("Wicket A")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(onClick = {
                                wicketsB++
                                commentary = "${match.teamB} lost wicket"
                                saveMatch()
                            }) {
                                Text("Wicket B")
                            }
                        }
                    }

                    "Kabaddi" -> {
                        Text(
                            "Kabaddi Controls",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(onClick = {
                                scoreA++
                                commentary = "${match.teamA} Raid Point"
                                winner = if (scoreA > scoreB) match.teamA else match.teamB
                                saveMatch()
                            }) {
                                Text("Raid A")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(onClick = {
                                scoreB++
                                commentary = "${match.teamB} Raid Point"
                                winner = if (scoreB > scoreA) match.teamB else match.teamA
                                saveMatch()
                            }) {
                                Text("Raid B")
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(onClick = {
                                scoreA += 2
                                commentary = "${match.teamA} All Out"
                                saveMatch()
                            }) {
                                Text("All Out A")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(onClick = {
                                scoreB += 2
                                commentary = "${match.teamB} All Out"
                                saveMatch()
                            }) {
                                Text("All Out B")
                            }
                        }
                    }

                    "Volleyball" -> {
                        Text(
                            "Volleyball Controls",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(onClick = {
                                scoreA++
                                commentary = "${match.teamA} scored point"

                                if (scoreA >= 25 && scoreA - scoreB >= 2) {
                                    setsA++
                                    scoreA = 0
                                    scoreB = 0
                                    commentary = "${match.teamA} won set"
                                }

                                winner = if (setsA >= 2) match.teamA else ""
                                saveMatch()
                            }) {
                                Text("+ Point A")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(onClick = {
                                scoreB++
                                commentary = "${match.teamB} scored point"

                                if (scoreB >= 25 && scoreB - scoreA >= 2) {
                                    setsB++
                                    scoreA = 0
                                    scoreB = 0
                                    commentary = "${match.teamB} won set"
                                }

                                winner = if (setsB >= 2) match.teamB else ""
                                saveMatch()
                            }) {
                                Text("+ Point B")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    "Winner: $winner",
                    color = Color.Green,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        scoreA = 0
                        scoreB = 0
                        wicketsA = 0
                        wicketsB = 0
                        setsA = 0
                        setsB = 0
                        winner = ""
                        commentary = "Match Reset"
                        saveMatch()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reset Match")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        db.child(match.matchId).removeValue()
                        onBack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Delete Match")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Back")
                }
            }
        }
    }
}