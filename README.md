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

Add screenshots here:

- Login Screen
- Register Screen
- Match Creation
- Cricket Live Scoring Screen
- Kabaddi Live Scoring Screen
- Volleyball Live Scoring Screen

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
