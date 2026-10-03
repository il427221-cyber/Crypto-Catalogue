package ru.netology.cryptocatalogue.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.viewmodel.CryptoViewModel

@Composable
fun CryptoList(
    viewModel: CryptoViewModel,
    onCoinClick: (CryptoCoin) -> Unit) {

    val state by viewModel.state.collectAsState()
    val list by viewModel.coins.collectAsState()

    when {
        state.loading -> CircularProgressIndicator()
        state.error -> Text("Error")
        else -> LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list) {coin ->
                CryptoCardSmall(coin = coin)
            }
        }
    }
}