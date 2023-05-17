package com.novandi.premierleaguematchday.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.novandi.premierleaguematchday.model.Club

@Composable
fun ClubDetailHeader(
    modifier: Modifier = Modifier,
    clubData: Club,
    onBackClick: () -> Unit,
    onAddFavorite: () -> Unit,
    onDeleteFavorite: () -> Unit
) {
    var favoriteIcon by remember {
        mutableStateOf(clubData.isFavorite)
    }
    Box(
        modifier = modifier
            .background(colorResource(id = clubData.color))
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = { onBackClick() },
            modifier = modifier
                .background(
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = CircleShape
                ),
            content = {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        )
        IconButton(
            onClick = {
                if (favoriteIcon) onDeleteFavorite() else onAddFavorite()
                favoriteIcon = !favoriteIcon
            },
            modifier = modifier
                .background(
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = CircleShape
                )
                .align(Alignment.TopEnd),
            content = {
                Icon(
                    imageVector = if (favoriteIcon)
                        Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        )
        AsyncImage(
            model = clubData.logoUrl,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .align(alignment = Alignment.Center)
        )
    }
}