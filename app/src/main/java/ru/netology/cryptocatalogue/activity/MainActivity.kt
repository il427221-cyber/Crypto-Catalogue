package ru.netology.cryptocatalogue.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.os.LocaleListCompat
import ru.netology.cryptocatalogue.navigation.NavGraph
import ru.netology.cryptocatalogue.ui.theme.CryptoCatalogueTheme
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Инициализируем оба менеджера настроек
        val themeSettings = ThemeSettings(applicationContext)
        val languageSettings = LanguageSettings(applicationContext)

        setContent {
            // 2. Подписываемся на Flow из DataStore для темы и языка и превращаем их в Compose-состояние.
            // Пока данные считываются в первый раз, дефолтное значение ставим false.
            val isDarkTheme by themeSettings.isDarkThemeFlow.collectAsState(initial = false)
            val currentLanguage by languageSettings.languageFlow.collectAsState(initial = LanguageSettings.SYSTEM)

            CryptoCatalogueTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                NavGraph(
                    isDarkTheme = isDarkTheme,
                    currentLanguage = currentLanguage,
                    onThemeChanged = {nextThemeValue ->
                        // 3. Запускаем корутину для безопасной записи нового значения
                        lifecycleScope.launch {
                            themeSettings.saveTheme(nextThemeValue)
                        }
                    },
                    onLanguageChanged = {nextLanguageValue ->
                        // 4. При смене языка сохраняем его в DataStore И применяем через AppCompat
                        lifecycleScope.launch {
                            languageSettings.saveLanguage(nextLanguageValue)
                        }
                        val localeList = if (nextLanguageValue == LanguageSettings.SYSTEM) {
                            LocaleListCompat.getEmptyLocaleList()
                        } else {
                            LocaleListCompat.forLanguageTags(nextLanguageValue)
                        }
                        AppCompatDelegate.setApplicationLocales(localeList)
                    }
                )
            }
        }
    }
}

