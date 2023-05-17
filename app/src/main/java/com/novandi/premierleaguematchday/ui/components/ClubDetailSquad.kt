package com.novandi.premierleaguematchday.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.novandi.premierleaguematchday.model.Squad

@Composable
fun ClubDetailSquad(data: Squad) {
    LazyColumn {
        items(data.squad, key = { it }) { player ->
            Text(
                text = player,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}