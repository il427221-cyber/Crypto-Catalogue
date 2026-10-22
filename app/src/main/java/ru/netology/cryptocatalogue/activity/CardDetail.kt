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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.netology.cryptocatalogue.R
import ru.netology.cryptocatalogue.viewmodel.CryptoViewModel

@Composable
fun CardDetail(
    coinId: String,
    snackBarHostState: SnackbarHostState,
    viewModel: CryptoViewModel) {

    val state by viewModel.state.collectAsState()
    val coinDetail by viewModel.coinDetail.collectAsState()

    val errorMessage = stringResource(R.string.failed_to_load_data)
    val retryMessage = stringResource(R.string.retry)
    val saveMessage = stringResource(R.string.save_data_shown)
    val refreshMessage = stringResource(R.string.refresh)

    val formatNumber = FormatNumbers()


    LaunchedEffect(coinId) {
        if (coinId.isNotEmpty()) {
            viewModel.loadCoinDetail(coinId)
        }
    }

    LaunchedEffect(state.error) {
        if (state.error && !state.loading) {
            val result = snackBarHostState.showSnackbar(
                message = errorMessage,
                actionLabel = retryMessage,
                duration = SnackbarDuration.Indefinite
            )
            if (result == SnackbarResult.ActionPerformed) {
                viewModel.loadCoinDetail(coinId)
            }
        }
    }

    LaunchedEffect(state.fromCache) {
        if (state.fromCache) {
            val result = snackBarHostState.showSnackbar(
                message = saveMessage,
                actionLabel = refreshMessage,
                duration = SnackbarDuration.Indefinite
            )
            if (result == SnackbarResult.ActionPerformed) {
                viewModel.loadCoinDetail(coinId)
            }
        }
    }

    when {
        state.loading && coinDetail == null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    coinDetail?.let { coin ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.inversePrimary),
                    contentAlignment = Alignment.Center

                ) {

                    Text(
                        stringResource(R.string.grid) + "${coin.rank}",
                        modifier = Modifier.padding(16.dp), fontSize = 20.sp
                    )

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

                        Text(stringResource(R.string.price), fontSize = 20.sp)
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            "${coin.price_usd}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(stringResource(R.string.usd), fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.padding(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.volume_24h), fontSize = 20.sp)
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = formatNumber.bigNumbersFormat(coin.volume24?: 0.0),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.padding(20.dp))

                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    HorizontalDivider(thickness = 4.dp)
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(stringResource(R.string.change_dynamics), fontSize = 25.sp)
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

                                Text(stringResource(R.string.__24h), fontSize = 20.sp)
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

                                Text(stringResource(R.string._1h), fontSize = 20.sp)
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

                                Text(stringResource(R.string._7d), fontSize = 20.sp)
                                Spacer(modifier = Modifier.padding(12.dp))
                                PercentChangeBadge(coin.percent_change_7d)
                            }
                        }

                    }

                }
            }
        }
    }
}