package ru.netology.cryptocatalogue.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import ru.netology.cryptocatalogue.activity.CryptoList
import ru.netology.cryptocatalogue.api.ApiClient
import ru.netology.cryptocatalogue.dao.CoinDao
import ru.netology.cryptocatalogue.repository.CryptoRepositoryImpl
import ru.netology.cryptocatalogue.viewmodel.CryptoViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val snackBarState = remember { SnackbarHostState() }

    val repository = CryptoRepositoryImpl(ApiClient.service)

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarState) }
    ) {innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "CryptoList",
            modifier = Modifier.padding(innerPadding))
        {
            val viewModel = CryptoViewModel(repository)

            composable("CryptoList") {
                CryptoList(
                    viewModel = viewModel,
                    snackBarHostState = snackBarState
                    ) { }
            }
        }
    }
}