package com.novandi.premierleaguematchday.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novandi.premierleaguematchday.data.ClubRepository
import com.novandi.premierleaguematchday.model.Club
import com.novandi.premierleaguematchday.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: ClubRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Club>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Club>>> get() = _uiState

    fun getClubFavorites() {
        viewModelScope.launch {
            repository.getClubFavorites()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { club ->
                    _uiState.value = UiState.Success(club)
                }
        }
    }
}