package com.novandi.premierleaguematchday.di

import com.novandi.premierleaguematchday.data.ClubRepository

object Injection {
    fun provideRepository(): ClubRepository = ClubRepository.getInstance()
}