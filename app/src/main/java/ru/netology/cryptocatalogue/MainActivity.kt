package ru.netology.cryptocatalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.netology.cryptocatalogue.navigation.NavGraph
import ru.netology.cryptocatalogue.ui.theme.CryptoCatalogueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCatalogueTheme {
                NavGraph()
            }
        }
    }
}

