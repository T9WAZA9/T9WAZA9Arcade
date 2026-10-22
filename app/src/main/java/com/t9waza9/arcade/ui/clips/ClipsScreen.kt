package com.t9waza9.arcade.ui.clips

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
 * Biblioteca de clips (Fase 1).
 * La lista real de videos grabados se implementa en la Fase 2, junto con
 * MediaProjection. Por ahora esta pantalla es el estado vacio verificable.
 */
@Composable
fun ClipsScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.nav_clips)) }) }
    ) { innerPadding ->
        T9EmptyState(
            title = stringResource(R.string.clips_empty_title),
            subtitle = stringResource(R.string.clips_empty_subtitle),
            modifier = Modifier.padding(innerPadding)
        )
    }
}
