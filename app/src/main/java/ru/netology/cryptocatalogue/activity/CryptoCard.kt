package ru.netology.cryptocatalogue.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.netology.cryptocatalogue.dto.CryptoCoin

@Composable
fun CryptoCard(coin: CryptoCoin) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("${coin.symbol} — ${coin.name}")
            Spacer(modifier = Modifier.padding(20.dp))
            Text("${coin.percent_change_24h}USD")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "${coin.price_usd}USD")
        }
    }
}

@Preview
@Composable
fun CryptoCardPreview (){
    CryptoCard(coin = CryptoCoin(
        symbol = "BTC",
        name = "Bitcoin",
        price_usd = "70.00",
        percent_change_24h = "+0,24"
    ))
}