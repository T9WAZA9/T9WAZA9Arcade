package com.t9waza9.arcade.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.t9waza9.arcade.R
import com.t9waza9.arcade.ui.components.T9EmptyState

/**
 * Pantalla principal (Fase 1).
 * Todavia no hay partidas grabadas (eso llega en la Fase 2), asi que
 * muestra el estado vacio correctamente en vez de simular datos falsos.
 */
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.home_title)) }) }
    ) { innerPadding ->
        T9EmptyState(
            title = stringResource(R.string.home_empty_title),
            subtitle = stringResource(R.string.home_empty_subtitle),
            modifier = Modifier.padding(innerPadding)
        )
    }
}
