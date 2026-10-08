package ru.netology.cryptocatalogue.repository

import ru.netology.cryptocatalogue.api.CryptoApi
import ru.netology.cryptocatalogue.dao.CoinDao
import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail
import ru.netology.cryptocatalogue.dto.ResultState
import ru.netology.cryptocatalogue.entity.toDto
import ru.netology.cryptocatalogue.entity.toEntity


class CryptoRepositoryImpl(
    private val api: CryptoApi,
    private val dao: CoinDao): CryptoRepository {

    override suspend fun getCoinsList(): ResultState<List<CryptoCoin>> {
        return try {
            val freshCoins = api.getCoinsList().data
            dao.insert(freshCoins.toEntity())
            ResultState.Success(freshCoins)
        } catch (_: Exception) {
            val cachedCoins = dao.getCoinsOnce().toDto()
            if (cachedCoins.isNotEmpty()) {
                ResultState.Success(cachedCoins, fromCache = true)
            } else {
                ResultState.Failure
            }
        }
    }
//    override suspend fun getCoinDetail(id: String): CryptoCoinDetail =
//        api.getCoinDetail(id)
//            .firstOrNull()
//            ?: throw IllegalStateException("Монета с id $id не найдена")
}