package ru.netology.cryptocatalogue.repository

import ru.netology.cryptocatalogue.api.CryptoApi
import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail

class CryptoRepositoryImpl(private val api: CryptoApi): CryptoRepository {
    override suspend fun getCoinsList(): List<CryptoCoin> = api.getCoinsList().data

    override suspend fun getCoinDetail(id: String): CryptoCoinDetail =
        api.getCoinDetail(id)
            .firstOrNull()
            ?: throw IllegalStateException("Монета с id $id не найдена")
}