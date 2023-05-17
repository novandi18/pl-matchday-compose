package com.novandi.premierleaguematchday.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import com.novandi.premierleaguematchday.model.Squad

@Composable
fun ClubDetailTab(
    matchday: List<List<String>>,
    squad: Squad
) {
    Column {
        var state by remember { mutableStateOf(0) }
        val tabs = listOf("Match History", "Squad")
        TabRow(selectedTabIndex = state) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = {
                        Text(
                            text = tab,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
        if (state == 0) ClubDetailMatchday(matchday) else ClubDetailSquad(squad)
    }
}