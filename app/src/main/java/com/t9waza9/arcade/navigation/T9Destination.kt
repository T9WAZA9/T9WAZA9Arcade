package com.t9waza9.arcade.navigation

/** Rutas de navegacion de la app. Fase 1: Home, Clips y Settings. */
sealed class T9Destination(val route: String) {
    data object Home : T9Destination("home")
    data object Clips : T9Destination("clips")
    data object Settings : T9Destination("settings")
}
