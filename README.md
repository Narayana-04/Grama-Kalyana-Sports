#  Grama-Kalyana Sports

A professional Android sports management application for creating, managing, and tracking live village sports tournaments.

##  Overview

Grama-Kalyana Sports is an Android application built to digitize village-level sports event management. The app allows organizers to create matches, manage teams, track live scores, and monitor match progress in real time.

Supported sports:
-  Cricket
-  Kabaddi
-  Volleyball

---

##  Features

### Authentication
- User Registration
- Secure Login
- Firebase Authentication integration

### Match Management
- Create new matches
- Add team names
- Add team leaders
- Add players
- Select venue
- Select date and time
- Sport selection

### Live Score Tracking

#### Cricket
- Run scoring (+1, +2, +3, +4, +6)
- Wicket tracking
- Winner detection
- Match commentary

#### Kabaddi
- Raid points
- All-out scoring
- Live commentary
- Winner tracking

#### Volleyball
- Point scoring
- Set tracking
- Automatic winner detection

### Additional Features
- Match reset
- Delete match
- Firebase Realtime Database sync
- Sports-themed UI
- Background images by sport

---

##  Tech Stack

- Kotlin
- Jetpack Compose
- Firebase Authentication
- Firebase Realtime Database
- Material 3
- Android Studio

---

##  Project Structure

```bash
app/
 ┣ manifests/
 ┣ kotlin+java/
 ┃ ┗ com.example.gramakalyanasports
 ┃    ┣ LoginActivity.kt
 ┃    ┣ RegisterActivity.kt
 ┃    ┣ MainActivity.kt
 ┃    ┣ Match.kt
 ┃    ┣ MatchRepository.kt
 ┃    ┣ ViewerScreen.kt
 ┃    ┣ HistoryScreen.kt
 ┃    ┣ LeaderboardScreen.kt
 ┃    ┗ NotificationHelper.kt
```

---

##  Installation

1. Clone repository

```bash
git clone https://github.com/Narayana-04/Grama-Kalyana-Sports.git
```

2. Open in Android Studio

3. Add Firebase configuration file:

```text
app/google-services.json
```

4. Sync Gradle

5. Run project

---

##  Screenshots

- Register Screen
<img width="720" height="1529" alt="register-screen jpeg" src="https://github.com/user-attachments/assets/429b85a9-2f8a-4f07-9e2a-e905d5356449" />

- Login Screen
<img width="720" height="1520" alt="login-screen jpeg" src="https://github.com/user-attachments/assets/2e2ff849-b0db-40f3-acac-2f45edeb8494" />

- Match Creation
<img width="549" height="850" alt="match-creation jpeg" src="https://github.com/user-attachments/assets/49f97d7f-c424-46bc-b451-f570de16b140" />

- Cricket Live Scoring Screen
<img width="720" height="1538" alt="cricket-live jpeg" src="https://github.com/user-attachments/assets/38fa69be-d86f-4c47-9d51-e07cc4628919" />

- Kabaddi Live Scoring Screen
<img width="720" height="1547" alt="kabaddi-live jpeg" src="https://github.com/user-attachments/assets/5f55adeb-f798-41a5-ac70-7fa3465c4078" />

- Volleyball Live Scoring Screen
<img width="720" height="1529" alt="volleyball-live jpeg" src="https://github.com/user-attachments/assets/0c83e429-0435-4639-bb18-c8ff10b8da36" />

---

##  Future Improvements

- Tournament brackets
- Push notifications
- Player profiles
- Team statistics
- Match reports PDF
- Admin dashboard improvements

---

##  Developer

**K Sharath Narayana**

Android Developer | Kotlin | Jetpack Compose | Firebase
