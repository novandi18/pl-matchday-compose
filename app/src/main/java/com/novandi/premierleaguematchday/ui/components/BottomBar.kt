package com.novandi.premierleaguematchday.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.novandi.premierleaguematchday.R
import com.novandi.premierleaguematchday.ui.navigation.NavigationItem
import com.novandi.premierleaguematchday.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val screens = listOf(
        NavigationItem(
            title = stringResource(id = R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.Home,
            contentDescription = stringResource(id = R.string.home_tab)
        ),
        NavigationItem(
            title = stringResource(id = R.string.menu_favorite),
            icon = Icons.Filled.Favorite,
            screen = Screen.Favorite,
            contentDescription = stringResource(id = R.string.favorite_tab)
        ),
        NavigationItem(
            title = stringResource(id = R.string.menu_profile),
            icon = Icons.Filled.Person,
            screen = Screen.Profile,
            contentDescription = stringResource(id = R.string.about_tab)
        )
    )

    NavigationBar {
        screens.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                label = { Text(text = item.title) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}