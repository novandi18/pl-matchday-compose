package com.novandi.premierleaguematchday.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.novandi.premierleaguematchday.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.novandi.premierleaguematchday.di.Injection
import com.novandi.premierleaguematchday.model.Club
import com.novandi.premierleaguematchday.ui.ViewModelFactory
import com.novandi.premierleaguematchday.ui.common.UiState
import com.novandi.premierleaguematchday.ui.components.ClubFavoriteItem

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getClubFavorites()
            }
            is UiState.Success -> {
                val data = uiState.data
                FavoriteContent(dataFavorite = data, navigateToDetail = navigateToDetail)
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    dataFavorite: List<Club>,
    navigateToDetail: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.favorite),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.purple)
                )
            )
        },
        content = { paddingValue ->
            Box(
                modifier = modifier.padding(paddingValue)
            ) {
                if (dataFavorite.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "No Favorite Club :(",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = colorResource(id = R.color.grey),
                        )
                    }
                } else {
                    LazyColumn {
                        items(dataFavorite, key = {it.id}) { club ->
                            ClubFavoriteItem(
                                name = club.name,
                                logoUrl = club.logoUrl,
                                stadium = club.stadium,
                                clubId = club.id,
                                navigateToDetail = navigateToDetail
                            )
                        }
                    }
                }
            }
        }
    )
}