package com.novandi.premierleaguematchday.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novandi.premierleaguematchday.data.ClubRepository
import com.novandi.premierleaguematchday.model.Club
import com.novandi.premierleaguematchday.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ClubDetailViewModel(private val repository: ClubRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Club>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Club>> get() = _uiState

    fun getClub(clubId: Int) {
        viewModelScope.launch {
            repository.getClubById(clubId = clubId)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { club ->
                    _uiState.value = UiState.Success(club)
                }
        }
    }

    fun addToFavorite(id: Int) {
        viewModelScope.launch {
            repository.setClubToFavorite(id)
        }
    }

    fun deleteFromFavorite(id: Int) {
        viewModelScope.launch {
            repository.removeClubFromFavorite(id)
        }
    }
}