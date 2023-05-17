package com.novandi.premierleaguematchday.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object ClubDetail : Screen("home/{clubId}") {
        fun createRoute(clubId: Int) = "home/$clubId"
    }
}