package com.t9waza9.arcade.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.t9waza9.arcade.R
import com.t9waza9.arcade.navigation.T9Destination

// Nota: se usan los iconos "core" de Material (Home, PlayArrow, Settings) a
// proposito, para no anadir la dependencia material-icons-extended solo por
// el icono VideoLibrary. Esto mantiene el proyecto con menos versiones que
// verificar y una descarga mas ligera en Acode/Termux/Actions.
private data class BottomItem(
    val destination: T9Destination,
    val icon: ImageVector,
    val labelRes: Int
)

@Composable
fun T9BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomItem(T9Destination.Home, Icons.Filled.Home, R.string.nav_home),
        BottomItem(T9Destination.Clips, Icons.Filled.PlayArrow, R.string.nav_clips),
        BottomItem(T9Destination.Settings, Icons.Filled.Settings, R.string.nav_settings)
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.destination.route,
                onClick = {
                    navController.navigate(item.destination.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(stringResource(item.labelRes)) }
            )
        }
    }
}
