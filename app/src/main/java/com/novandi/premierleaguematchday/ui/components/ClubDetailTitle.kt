package com.novandi.premierleaguematchday.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.novandi.premierleaguematchday.R

@Composable
fun ClubDetailTitle(
    modifier: Modifier = Modifier,
    name: String,
    stadium: String
) {
    Column(
        modifier = modifier.padding(20.dp)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = modifier.padding(bottom = 4.dp)
        )
        Text(
            text = stadium,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = colorResource(id = R.color.grey)
        )
    }
}