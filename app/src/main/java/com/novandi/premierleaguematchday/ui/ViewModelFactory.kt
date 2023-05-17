package com.novandi.premierleaguematchday.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.novandi.premierleaguematchday.data.ClubRepository
import com.novandi.premierleaguematchday.ui.screen.detail.ClubDetailViewModel
import com.novandi.premierleaguematchday.ui.screen.favorite.FavoriteViewModel
import com.novandi.premierleaguematchday.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ClubRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ClubDetailViewModel::class.java)) {
            return ClubDetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}