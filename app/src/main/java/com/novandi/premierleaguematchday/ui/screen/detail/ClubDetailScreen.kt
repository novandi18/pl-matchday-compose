package com.novandi.premierleaguematchday.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.novandi.premierleaguematchday.R
import com.novandi.premierleaguematchday.di.Injection
import com.novandi.premierleaguematchday.model.Club
import com.novandi.premierleaguematchday.model.ClubDataSource
import com.novandi.premierleaguematchday.ui.ViewModelFactory
import com.novandi.premierleaguematchday.ui.common.UiState
import com.novandi.premierleaguematchday.ui.components.ClubDetailHeader
import com.novandi.premierleaguematchday.ui.components.ClubDetailTab
import com.novandi.premierleaguematchday.ui.components.ClubDetailTitle
import com.novandi.premierleaguematchday.ui.theme.PremierLeagueMatchdayTheme

@Composable
fun ClubDetailScreen(
    viewModel: ClubDetailViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    clubId: Int,
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getClub(clubId)
            }
            is UiState.Success -> {
                val data = uiState.data
                    ClubDetailContent(
                        clubData = data,
                        onBackClick = navigateBack,
                        onAddFavorite = {
                            viewModel.addToFavorite(clubId)
                        },
                        onDeleteFavorite = {
                            viewModel.deleteFromFavorite(clubId)
                        }
                    )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun ClubDetailContent(
    modifier: Modifier = Modifier,
    clubData: Club,
    onBackClick: () -> Unit,
    onAddFavorite: () -> Unit,
    onDeleteFavorite: () -> Unit
) {
    Column {
        ClubDetailHeader(
            modifier = modifier,
            clubData = clubData,
            onBackClick = { onBackClick() },
            onAddFavorite = onAddFavorite,
            onDeleteFavorite = onDeleteFavorite
        )
        ClubDetailTitle(
            modifier = modifier,
            name = clubData.name,
            stadium = clubData.stadium
        )
        ClubDetailTab(
            matchday = clubData.matchday,
            squad = clubData.squad
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ClubDetailContentPreview() {
    PremierLeagueMatchdayTheme {
        ClubDetailContent(
            clubData = Club(
                1,
                "Bournemouth",
                "Vitality Stadium",
                "",
                R.color.bournemouth,
                ClubDataSource.squadClubs[0],
                ClubDataSource.dummyMatchday[0]
            ),
            onBackClick = {},
            onAddFavorite = {},
            onDeleteFavorite = {}
        )
    }
}