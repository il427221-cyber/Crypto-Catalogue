package ru.netology.cryptocatalogue.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.cryptocatalogue.dto.CryptoCoin

@Composable
fun CryptoList(
    coins: List<CryptoCoin>,
    onCoinClick: (CryptoCoin) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(coins) {coin ->
            CryptoCard(coin = coin)
        }
    }
}

@Preview
@Composable
fun CryptoListPreview() {
    CryptoList(
        coins = listOf(
            CryptoCoin(symbol = "BTC", name = "Bitcoin", price_usd = "70000.00", percent_change_24h = "+0.24"),
            CryptoCoin(symbol = "ETH", name = "Ethereum", price_usd = "3500.00", percent_change_24h = "-1.15"),
            CryptoCoin(symbol = "SOL", name = "Solana", price_usd = "150.00", percent_change_24h = "+5.40")
        ),
    ) {}
}