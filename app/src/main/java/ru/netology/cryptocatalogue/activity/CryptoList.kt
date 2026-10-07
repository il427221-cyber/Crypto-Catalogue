package ru.netology.cryptocatalogue.activity


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.netology.cryptocatalogue.R
import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.viewmodel.CryptoViewModel


@Composable
fun CryptoList(
    viewModel: CryptoViewModel,
    snackBarHostState: SnackbarHostState,
    onCoinClick: (CryptoCoin) -> Unit) {

    val state by viewModel.state.collectAsState()
    val coins by viewModel.coins.collectAsState()

    val errorMessage = stringResource(R.string.failed_to_load_data)
    val retryMessage = stringResource(R.string.retry)
    val saveMessage = stringResource(R.string.save_data_shown)
    val refreshMessage = stringResource(R.string.refresh)

    LaunchedEffect(state.error, state.loading) {
        when {
            state.error && !state.loading -> {
                val result = snackBarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = retryMessage,
                    duration = SnackbarDuration.Indefinite
                )
                if (result == SnackbarResult.ActionPerformed) viewModel.loadCoinsList()
            }
            state.fromCache -> {
                val result = snackBarHostState.showSnackbar(
                    message = saveMessage,
                    actionLabel = refreshMessage,
                    duration = SnackbarDuration.Indefinite
                )
                if (result == SnackbarResult.ActionPerformed) viewModel.loadCoinsList()
            }
        }
    }

    when {
        state.loading && coins.isEmpty()-> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        coins.isNotEmpty() -> {
            Column {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(coins) {coin ->
                        CryptoCardSmall(coin = coin)
                    }
                }
            }
        }
    }
}