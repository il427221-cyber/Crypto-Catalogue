package ru.netology.cryptocatalogue.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import ru.netology.cryptocatalogue.activity.CardDetail
import ru.netology.cryptocatalogue.activity.CryptoList
import ru.netology.cryptocatalogue.activity.SettingsScreen
import ru.netology.cryptocatalogue.viewmodel.CryptoViewModel

@Composable
fun NavGraph(
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    val snackBarState = remember { SnackbarHostState() }

    val viewModel: CryptoViewModel = hiltViewModel()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarState) }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "CryptoList",
            modifier = Modifier.padding(innerPadding))
        {

            composable("CryptoList") {
                CryptoList(
                    viewModel = viewModel,
                    snackBarHostState = snackBarState,
                    navController = navController
                    )
            }

            composable("CardDetail/{coinId}") {backStackEntry ->
                val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
                CardDetail(
                    coinId = coinId,
                    snackBarHostState = snackBarState,
                    viewModel = viewModel
                )
            }

            composable("Settings") {
                SettingsScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeChanged = onThemeChanged,
                    onBackClick = { navController.popBackStack() },
                    viewModel = viewModel
                )
            }
        }
    }
}