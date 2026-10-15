package ru.netology.cryptocatalogue.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.cryptocatalogue.dto.CoinListResponse
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail

interface CryptoApi {
    @GET("tickers/")
    suspend fun getCoinsList(
        @Query("start") start: Int = 0,
        @Query("limit") limit: Int = 20
    ): CoinListResponse

    @GET("ticker/")
    suspend fun getCoinDetail(@Query("id") id:String): List<CryptoCoinDetail>
}