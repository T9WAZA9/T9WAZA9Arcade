package com.t9waza9.arcade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.t9waza9.arcade.data.AppPreferences
import com.t9waza9.arcade.navigation.T9NavHost
import com.t9waza9.arcade.ui.components.T9BottomBar
import com.t9waza9.arcade.ui.theme.T9Waza9ArcadeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val preferences = AppPreferences(applicationContext)

        setContent {
            var darkTheme by remember { mutableStateOf(preferences.isDarkTheme) }

            T9Waza9ArcadeTheme(darkTheme = darkTheme) {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { T9BottomBar(navController = navController) }
                ) { innerPadding ->
                    T9NavHost(
                        navController = navController,
                        preferences = preferences,
                        onThemeChanged = { darkTheme = it },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
