package ru.netology.cryptocatalogue.activity

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 1. Создаем делегат для инициализации DataStore (база данных будет называться settings.preferences_pb)
private val Context.dataStore by preferencesDataStore(name = "settings")

class ThemeSettings(private val context: Context) {

    companion object {
        // 2. Создаем уникальный ключ для сохранения флага темы (как имя колонки в таблице)
        val DARK_THEME_KEY = booleanPreferencesKey("is_dark_theme")
    }

    // 3. Читаем тему: возвращает поток Flow<Boolean>. Если данных еще нет в памяти, по умолчанию будет false (светлая)
    val isDarkThemeFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_THEME_KEY] ?:false
        }

    // 4. Записываем тему в память (синхронно-безопасно в фоне)
    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = isDark
        }
    }
}