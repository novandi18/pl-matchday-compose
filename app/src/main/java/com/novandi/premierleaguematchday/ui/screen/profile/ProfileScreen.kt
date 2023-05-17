package com.novandi.premierleaguematchday.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.novandi.premierleaguematchday.R
import com.novandi.premierleaguematchday.ui.theme.PremierLeagueMatchdayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = stringResource(id = R.string.profile),
                    color = Color.White
                ) },
                colors = TopAppBarDefaults
                    .smallTopAppBarColors(
                        containerColor = colorResource(
                            id = R.color.purple
                        )
                    ),
            )
        },
        content = {
            ProfileContent(
                modifier = modifier,
                paddingValues = it
            )
        }
    )
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues
) {
    Column(
        modifier = modifier
            .padding(24.dp)
            .padding(paddingValues),
    ) {
        Card(
            modifier = modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.author),
                contentDescription = stringResource(id = R.string.author_name)
            )
        }
        Text(
            text = stringResource(id = R.string.author_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.author_email),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PremierLeagueMatchdayTheme {
        ProfileScreen()
    }
}