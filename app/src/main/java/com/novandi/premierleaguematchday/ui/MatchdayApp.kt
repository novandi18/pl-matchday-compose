package com.novandi.premierleaguematchday.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.novandi.premierleaguematchday.ui.components.BottomBar
import com.novandi.premierleaguematchday.ui.navigation.Screen
import com.novandi.premierleaguematchday.ui.screen.detail.ClubDetailScreen
import com.novandi.premierleaguematchday.ui.screen.favorite.FavoriteScreen
import com.novandi.premierleaguematchday.ui.screen.home.HomeScreen
import com.novandi.premierleaguematchday.ui.screen.profile.ProfileScreen
import com.novandi.premierleaguematchday.ui.theme.PremierLeagueMatchdayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchdayApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.ClubDetail.route) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { clubId ->
                        navController.navigate(Screen.ClubDetail.createRoute(clubId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { clubId ->
                        navController.navigate(Screen.ClubDetail.createRoute(clubId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.ClubDetail.route,
                arguments = listOf(navArgument("clubId") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("clubId") ?: -1
                ClubDetailScreen(
                    clubId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchdayAppPreview() {
    PremierLeagueMatchdayTheme {
        MatchdayApp()
    }
}