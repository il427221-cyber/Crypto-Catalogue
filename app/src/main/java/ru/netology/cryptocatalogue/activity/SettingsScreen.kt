package ru.netology.cryptocatalogue.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.netology.cryptocatalogue.R
import ru.netology.cryptocatalogue.viewmodel.CryptoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    viewModel: CryptoViewModel
    ) {
    // 1. Получаем оригинальный полный список монет для выпадающего меню
    val allCoins by viewModel.coins.collectAsState()
    // 2. Получаем текущее множество ID выбранных монет, чтобы знать, где ставить галочки
    val selectedIds by viewModel.selectedCoinIds.collectAsState()
    // 3. Переменная состояния: раскрыто ли меню прямо сейчас
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.dark_theme),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = stringResource(R.string.switch_between_light_and_dark_themes),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Switch(checked = isDarkTheme, onCheckedChange = { onThemeChanged(it) })
            }
            Spacer(modifier = Modifier.padding(12.dp))
            HorizontalDivider(thickness = 1.dp)
            Spacer(modifier = Modifier.padding(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = stringResource(R.string.list_filter),
                        fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    Text(
                        text = if (selectedIds.isEmpty())
                            stringResource(R.string.all_coins_are_shown)
                            else
                            stringResource(R.string.coins_selected, selectedIds.size),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }
                if (selectedIds.isNotEmpty()) {
                    TextButton(onClick = { viewModel.clearAllFilters() }) {
                        Text(stringResource(R.string.clear))
                    }
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            ExposedDropdownMenuBox(
                expanded = isMenuExpanded,
                onExpandedChange = { isMenuExpanded = !isMenuExpanded }
            ) {
                OutlinedTextField(
                    value = if (selectedIds.isEmpty())
                        stringResource(R.string.select_coins)
                        else
                        stringResource(R.string.coins_selected, selectedIds.size),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded) },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(
                            ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                            enabled = true
                        )
                        .exposedDropdownSize()
                )

                ExposedDropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    allCoins.forEach {coin ->
                        // Проверяем, выбран ли текущий элемент
                        val isChecked = selectedIds.contains(coin.id)

                        DropdownMenuItem(
                            text = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = null // null, чтобы клик обрабатывался по всей строке меню
                                    )
                                    Spacer(modifier = Modifier.padding(8.dp))
                                    Text(text = "${coin.name} (${coin.symbol})")

                                }
                            },
                            onClick = {
                                // Вызываем нашу вторую функцию из вьюмодели
                                viewModel.toggleCoinSelection(coin.id)
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }
    }
}
