package com.novandi.premierleaguematchday.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.novandi.premierleaguematchday.R

@Composable
fun ClubDetailMatchday(
    data: List<List<String>>
) {
    LazyColumn {
        items(data.reversed(), key = null) { match ->
            Column {
                Box(
                    modifier = Modifier
                        .background(colorResource(id = R.color.light))
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Matchday ${match[0]}",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    text = "vs ${match[1]} (${match[2]})",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}