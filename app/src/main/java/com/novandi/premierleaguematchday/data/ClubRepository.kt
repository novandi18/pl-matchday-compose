package com.novandi.premierleaguematchday.data

import com.novandi.premierleaguematchday.model.Club
import com.novandi.premierleaguematchday.model.ClubDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ClubRepository {
    fun getClubs(): Flow<List<Club>> = flowOf(ClubDataSource.dummyClubs)

    fun getClubFavorites(): Flow<List<Club>> = flowOf(ClubDataSource.dummyClubs.filter { it.isFavorite })

    fun searchClubs(query: String): Flow<List<Club>> = flowOf(ClubDataSource.dummyClubs.filter {
        it.name.contains(query, ignoreCase = true)
    })

    fun getClubById(clubId: Int): Flow<Club> {
        val index = ClubDataSource.dummyClubs.indexOfFirst { it.id == clubId }
        return flowOf(ClubDataSource.dummyClubs[index])
    }

    fun setClubToFavorite(clubId: Int): Flow<Boolean> {
        val index = ClubDataSource.dummyClubs.indexOfFirst { it.id == clubId }
        val result = if (index >= 0 && !ClubDataSource.dummyClubs[index].isFavorite) {
            ClubDataSource.dummyClubs[index].isFavorite = true
            true
        } else false
        return flowOf(result)
    }

    fun removeClubFromFavorite(clubId: Int): Flow<Boolean> {
        val index = ClubDataSource.dummyClubs.indexOfFirst { it.id == clubId }
        val club = ClubDataSource.dummyClubs[index]
        val result = if (club.isFavorite) {
            club.isFavorite = false
            true
        } else false
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: ClubRepository? = null

        fun getInstance(): ClubRepository = instance ?: synchronized(this) {
            ClubRepository().apply {
                instance = this
            }
        }
    }
}