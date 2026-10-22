package com.t9waza9.arcade.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.t9waza9.arcade.data.AppPreferences
import com.t9waza9.arcade.ui.clips.ClipsScreen
import com.t9waza9.arcade.ui.home.HomeScreen
import com.t9waza9.arcade.ui.settings.SettingsScreen

@Composable
fun T9NavHost(
    navController: NavHostController,
    preferences: AppPreferences,
    onThemeChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = T9Destination.Home.route,
        modifier = modifier
    ) {
        composable(T9Destination.Home.route) { HomeScreen() }
        composable(T9Destination.Clips.route) { ClipsScreen() }
        composable(T9Destination.Settings.route) {
            SettingsScreen(preferences = preferences, onThemeChanged = onThemeChanged)
        }
    }
}
