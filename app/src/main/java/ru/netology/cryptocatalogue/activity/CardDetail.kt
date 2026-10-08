package ru.netology.cryptocatalogue.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail

@Composable
fun CardDetail(coin: CryptoCoinDetail) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(modifier = Modifier.padding(16.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.inversePrimary),
                contentAlignment = Alignment.Center

            ) {

            Text("#" + "${coin.rank}",
                modifier = Modifier.padding(16.dp), fontSize = 20.sp)

        }

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(coin.symbol, fontWeight = FontWeight.Bold, fontSize = 40.sp)
                Spacer(modifier = Modifier.padding(8.dp))
                Text(coin.name, fontSize = 30.sp)
                Spacer(modifier = Modifier.padding(20.dp))
                HorizontalDivider(thickness = 4.dp)
                Spacer(modifier = Modifier.padding(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Price:",fontSize = 20.sp)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text("${coin.price_usd}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text("USD", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.padding(12.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volume 24h",fontSize = 20.sp)
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text("${coin.volume24}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp)
                    Spacer(modifier = Modifier.padding(20.dp))

                }
                Spacer(modifier = Modifier.padding(20.dp))
                HorizontalDivider(thickness = 4.dp)
                Spacer(modifier = Modifier.padding(20.dp))
                Text("Change Dynamics (%):", fontSize = 25.sp)
                Spacer(modifier = Modifier.padding(20.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()

                ) {

                    OutlinedCard {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text("24h", fontSize = 20.sp)
                            Spacer(modifier = Modifier.padding(12.dp))
                            PercentChangeBadge(coin.percent_change_24h)
                        }
                    }

                    OutlinedCard {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text("1h", fontSize = 20.sp)
                            Spacer(modifier = Modifier.padding(12.dp))
                            PercentChangeBadge(coin.percent_change_1h)
                        }
                    }

                    OutlinedCard {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text("7d", fontSize = 20.sp)
                            Spacer(modifier = Modifier.padding(12.dp))
                            PercentChangeBadge(coin.percent_change_7d)
                        }
                    }

                }

            }
        }
    }
}

@Preview
@Composable
fun CardDetailPreview (){
    CardDetail(coin = CryptoCoinDetail(
        symbol = "BTC",
        name = "Bitcoin",
        rank = 1,
        price_usd = "80.00",
        percent_change_24h = "+0.04",
        percent_change_1h = "0.0",
        percent_change_7d = "-0.24",
        volume24 = 345465667.88
    ))
}