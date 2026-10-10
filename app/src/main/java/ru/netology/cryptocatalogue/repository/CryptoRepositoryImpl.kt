package ru.netology.cryptocatalogue.repository

import ru.netology.cryptocatalogue.api.CryptoApi
import ru.netology.cryptocatalogue.dao.CoinDao
import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail
import ru.netology.cryptocatalogue.dto.ResultState
import ru.netology.cryptocatalogue.entity.CoinDetailEntity
import ru.netology.cryptocatalogue.entity.toDto
import ru.netology.cryptocatalogue.entity.toEntity


class CryptoRepositoryImpl(
    private val api: CryptoApi,
    private val dao: CoinDao): CryptoRepository {

    override suspend fun getCoinsList(): ResultState<List<CryptoCoin>> {
        return try {
            val freshCoins = api.getCoinsList().data
            dao.insertList(freshCoins.toEntity())
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

    override suspend fun getCoinDetail(id: String): ResultState<CryptoCoinDetail> {
       return try {
           val response = api.getCoinDetail(id)
           val coin = response.data ?: return ResultState.Failure
           val entity = CoinDetailEntity.fromDto(coin)
           dao.insert(entity)
           ResultState.Success(coin, fromCache = false)
       } catch (_: Exception) {
           val cachedCoin = dao.getCoinDetailById(id)
           if (cachedCoin != null) {
               ResultState.Success(cachedCoin.toDto(), fromCache = true)
           } else {
               ResultState.Failure
           }
       }
       }
}