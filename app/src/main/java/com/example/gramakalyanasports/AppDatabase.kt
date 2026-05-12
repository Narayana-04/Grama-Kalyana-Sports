package com.example.gramakalyanasports

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MatchEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao
}