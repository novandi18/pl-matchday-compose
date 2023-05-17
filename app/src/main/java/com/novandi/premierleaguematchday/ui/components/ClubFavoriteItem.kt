package com.novandi.premierleaguematchday.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.novandi.premierleaguematchday.R
import com.novandi.premierleaguematchday.ui.theme.PremierLeagueMatchdayTheme

@Composable
fun ClubFavoriteItem(
    modifier: Modifier = Modifier,
    clubId: Int,
    name: String,
    logoUrl: String,
    stadium: String,
    navigateToDetail: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { navigateToDetail(clubId) }
            .padding(16.dp)
    ) {
        AsyncImage(
            model = logoUrl,
            contentDescription = name,
            modifier = modifier.size(60.dp)
        )
        Column(
            modifier = modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.padding(bottom = 4.dp),
                maxLines = 1
            )
            Text(
                text = stadium,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                maxLines = 1
            )
        }
    }
    Divider(
        color = colorResource(id = R.color.light),
        modifier = modifier
            .fillMaxWidth()
            .width(1.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    PremierLeagueMatchdayTheme {
        ClubFavoriteItem(
            clubId = 1,
            name = "Bournemouth",
            stadium = "Vitality Stadium",
            logoUrl = "",
            navigateToDetail = {}
        )
    }
}