package com.novandi.premierleaguematchday.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novandi.premierleaguematchday.data.ClubRepository
import com.novandi.premierleaguematchday.model.Club
import com.novandi.premierleaguematchday.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ClubRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Club>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Club>>> get() = _uiState

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

    fun search(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            repository.searchClubs(_searchQuery.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _uiState.value = UiState.Success(result)
                }
        }
    }

    fun getAllClubs() {
        viewModelScope.launch {
            repository.getClubs()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { clubs ->
                    _uiState.value = UiState.Success(clubs)
                }
        }
    }
}