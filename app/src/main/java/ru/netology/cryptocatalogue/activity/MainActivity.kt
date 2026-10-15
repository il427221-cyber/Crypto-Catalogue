package ru.netology.cryptocatalogue.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.netology.cryptocatalogue.navigation.NavGraph
import ru.netology.cryptocatalogue.ui.theme.CryptoCatalogueTheme
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Инициализируем наш менеджер настроек
        val themeSettings = ThemeSettings(applicationContext)

        setContent {
            // 2. Подписываемся на Flow из DataStore и превращаем его в Compose-состояние.
            // Пока данные считываются в первый раз, дефолтное значение ставим false.
            val isDarkTheme by themeSettings.isDarkThemeFlow.collectAsState(initial = false)

            CryptoCatalogueTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                NavGraph(
                    isDarkTheme = isDarkTheme,
                    onThemeChanged = {nextThemeValue ->
                        // 3. Запускаем корутину для безопасной записи нового значения
                        lifecycleScope.launch {
                            themeSettings.saveTheme(nextThemeValue)
                        }
                    }
                )
            }
        }
    }
}

