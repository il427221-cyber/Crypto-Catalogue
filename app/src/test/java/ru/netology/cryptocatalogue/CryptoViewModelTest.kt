package ru.netology.cryptocatalogue

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail
import ru.netology.cryptocatalogue.dto.ResultState
import ru.netology.cryptocatalogue.repository.CryptoRepository
import ru.netology.cryptocatalogue.viewmodel.CryptoViewModel

@OptIn(ExperimentalCoroutinesApi::class)
class CryptoViewModelTest {
    // 1. Используем UnconfinedTestDispatcher, чтобы корутины в viewModelScope выполнялись мгновенно и синхронно
    private val testDispatcher = UnconfinedTestDispatcher()

    // Переменная для фейкового репозитория
    private lateinit var fakeRepository: FakeCryptoRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeCryptoRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun toggleCoinSelection() {
        // Тест 1: Проверяем логику выбора монет (добавление/удаление из Set)
        val viewModel = CryptoViewModel(fakeRepository)
        val coinId = "bitcoin"

        // Выбираем монету
        viewModel.toggleCoinSelection(coinId)
        assertTrue(viewModel.selectedCoinIds.value.contains(coinId))

        // Отменяем выбор
        viewModel.toggleCoinSelection(coinId)
        assertTrue(viewModel.selectedCoinIds.value.isEmpty())
    }

    @Test
    fun showFilteredCoins() {
        fakeRepository.fakeCoins = listOf(
            CryptoCoin(id = "bitcoin", name = "Bitcoin"),
            CryptoCoin(id = "ethereum", name = "Ethereum"),
        )

        val viewModel = CryptoViewModel(fakeRepository)

        // Выбираем Биткоин
        viewModel.toggleCoinSelection("bitcoin")

        // Проверяем, что в отфильтрованном списке остался только Биткоин
        assertEquals(1,viewModel.filteredCoins.value.size)
        assertEquals("bitcoin",viewModel.filteredCoins.value.first().id)
    }

    @Test
    fun loadCoinsListError() {
        // Тест 3: Проверяем реакцию ViewModel на ошибку репозитория
        fakeRepository.shouldReturnError = true

        val viewModel = CryptoViewModel(fakeRepository)

        // Проверяем, что state перешел в состояние ошибки
        assertTrue(viewModel.state.value.error)
        assertFalse(viewModel.state.value.loading)
        assertTrue(viewModel.coins.value.isEmpty())
    }
}

// Вспомогательный класс-заглушка вместо реального репозитория
class FakeCryptoRepository: CryptoRepository {
    var fakeCoins: List<CryptoCoin> = emptyList()
    var fakeCoinDetail: CryptoCoinDetail? = null
    var shouldReturnError = false

    override suspend fun getCoinsList(): ResultState<List<CryptoCoin>> {
        return if(shouldReturnError) {
            ResultState.Failure
        } else {
            ResultState.Success(data = fakeCoins, fromCache = false)
        }
    }

    override suspend fun getCoinDetail(id: String): ResultState<CryptoCoinDetail> {
       val detail = fakeCoinDetail
        return if (shouldReturnError || detail == null) {
            ResultState.Failure
        } else {
            ResultState.Success(data = detail, fromCache = false)
        }
    }
}