package com.novandi.premierleaguematchday.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.novandi.premierleaguematchday.R
import com.novandi.premierleaguematchday.di.Injection
import com.novandi.premierleaguematchday.model.Club
import com.novandi.premierleaguematchday.ui.ViewModelFactory
import com.novandi.premierleaguematchday.ui.common.UiState
import com.novandi.premierleaguematchday.ui.components.ClubItem
import com.novandi.premierleaguematchday.ui.components.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Int) -> Unit
) {
    val query by viewModel.searchQuery

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllClubs()
            }
            is UiState.Success -> {
                HomeContent(
                    query = query,
                    clubs = uiState.data,
                    modifier = modifier,
                    viewModel = viewModel,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    query: String,
    clubs: List<Club>,
    viewModel: HomeViewModel,
    navigateToDetail: (Int) -> Unit
) {
    Box(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            content = {
                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    },
                ) {
                    SearchBar(
                        query = query,
                        onQueryChange = viewModel::search,
                        modifier = Modifier.background(colorResource(id = R.color.purple))
                    )
                }
                items(clubs, key = { it.id }) { club ->
                    Box(modifier = modifier.padding(8.dp)) {
                        ClubItem(
                            name = club.name,
                            stadium = club.stadium,
                            logoUrl = club.logoUrl,
                            color = club.color,
                            modifier = modifier.clickable {
                                navigateToDetail(club.id)
                            }
                        )
                    }
                }
            }
        )
    }
}