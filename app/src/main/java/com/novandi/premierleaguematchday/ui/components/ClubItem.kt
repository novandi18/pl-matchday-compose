package com.novandi.premierleaguematchday.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.novandi.premierleaguematchday.R
import com.novandi.premierleaguematchday.ui.theme.PremierLeagueMatchdayTheme

@Composable
fun ClubItem(
    name: String,
    stadium: String,
    logoUrl: String,
    color: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp)),
    ) {
        Box(
            modifier = modifier
                .background(colorResource(id = color))
                .padding(12.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .background(color = Color.White, shape = CircleShape)
                    .height(90.dp)
                    .width(90.dp),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = logoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .align(alignment = Alignment.Center)
                )
            }
        }
        Column(
            modifier = modifier
                .background(colorResource(id = R.color.light))
                .padding(8.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )
            Text(
                text = stadium,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClubListItemPreview() {
    PremierLeagueMatchdayTheme {
        ClubItem(
            name = "Manchester United",
            stadium = "Old Trafford",
            logoUrl = "",
            color = R.color.manunited
        )
    }
}