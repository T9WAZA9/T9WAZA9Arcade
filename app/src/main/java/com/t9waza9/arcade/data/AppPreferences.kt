package com.t9waza9.arcade.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Persistencia local simple basada en SharedPreferences.
 *
 * Suficiente para los ajustes de la Fase 1 (tema, primer arranque).
 * Si en fases futuras hace falta guardar listas o datos estructurados
 * (por ejemplo, metadatos de clips grabados), conviene migrar a
 * androidx.datastore:datastore-preferences en vez de anadir mas claves aqui.
 */
class AppPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var isDarkTheme: Boolean
        get() = prefs.getBoolean(KEY_DARK_THEME, true)
        set(value) = prefs.edit().putBoolean(KEY_DARK_THEME, value).apply()

    var isFirstLaunch: Boolean
        get() = prefs.getBoolean(KEY_FIRST_LAUNCH, true)
        set(value) = prefs.edit().putBoolean(KEY_FIRST_LAUNCH, value).apply()

    companion object {
        private const val PREFS_NAME = "t9waza9_prefs"
        private const val KEY_DARK_THEME = "dark_theme"
        private const val KEY_FIRST_LAUNCH = "first_launch"
    }
}
