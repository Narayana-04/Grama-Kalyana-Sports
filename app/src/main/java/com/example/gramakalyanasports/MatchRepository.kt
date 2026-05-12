package com.example.gramakalyanasports

class MatchRepository(private val matchDao: MatchDao) {

    suspend fun insertMatch(match: MatchEntity) {
        matchDao.insertMatch(match)
    }

    suspend fun getAllMatches(): List<MatchEntity> {
        return matchDao.getAllMatches()
    }

    suspend fun updateMatch(match: MatchEntity) {
        matchDao.updateMatch(match)
    }
}