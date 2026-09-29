package ru.netology.cryptocatalogue.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import ru.netology.cryptocatalogue.activity.CryptoList
import ru.netology.cryptocatalogue.dto.CryptoCoin

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    Scaffold {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "CryptoList",
            modifier = Modifier.padding(innerPadding))
        {
            composable("CryptoList") {
                CryptoList(listOf(
                    CryptoCoin(symbol = "BTC", name = "Bitcoin", price_usd = "70000.00", percent_change_24h = "+0.24"),
                    CryptoCoin(symbol = "ETH", name = "Ethereum", price_usd = "3500.00", percent_change_24h = "-1.15"),
                    CryptoCoin(symbol = "SOL", name = "Solana", price_usd = "150.00", percent_change_24h = "+5.40")
                )) { }
            }
        }
    }
}