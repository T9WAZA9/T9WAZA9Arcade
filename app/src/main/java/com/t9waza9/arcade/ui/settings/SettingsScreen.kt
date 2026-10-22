package com.t9waza9.arcade.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t9waza9.arcade.BuildConfig
import com.t9waza9.arcade.R
import com.t9waza9.arcade.data.AppPreferences

/**
 * Ajustes (Fase 1): el interruptor de tema SI funciona de verdad (se guarda
 * en AppPreferences y cambia el ColorScheme en MainActivity). El resto de
 * ajustes previstos en el prompt original (calidad de grabacion, audio,
 * calidad de emision) se anadiran cuando esas funciones existan: no tiene
 * sentido mostrar un control que no cambia nada todavia.
 */
@Composable
fun SettingsScreen(
    preferences: AppPreferences,
    onThemeChanged: (Boolean) -> Unit
) {
    var darkTheme by remember { mutableStateOf(preferences.isDarkTheme) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.settings_title)) }) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ListItem(
                headlineContent = { Text(stringResource(R.string.settings_theme)) },
                trailingContent = {
                    Switch(
                        checked = darkTheme,
                        onCheckedChange = { checked ->
                            darkTheme = checked
                            preferences.isDarkTheme = checked
                            onThemeChanged(checked)
                        }
                    )
                }
            )
            ListItem(
                headlineContent = { Text(stringResource(R.string.settings_about)) },
                supportingContent = {
                    Text(stringResource(R.string.settings_version, BuildConfig.VERSION_NAME))
                }
            )
        }
    }
}
