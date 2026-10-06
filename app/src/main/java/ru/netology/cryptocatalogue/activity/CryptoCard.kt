package ru.netology.cryptocatalogue.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.netology.cryptocatalogue.dto.CryptoCoin

@Composable
fun CryptoCardSmall(coin: CryptoCoin) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(coin.symbol, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text("${coin.percent_change_24h}")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text("USD")
                    Spacer(modifier = Modifier.weight(1f))
                    Text("${coin.price_usd}")
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text("USD")
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(coin.name, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                }
            }
    }
}

@Preview
@Composable
fun CryptoCardPreview (){
    CryptoCardSmall(coin = CryptoCoin(
        symbol = "BTC",
        name = "Bitcoin",
        price_usd = "70.00",
        percent_change_24h = "+0,24"
    ))
}