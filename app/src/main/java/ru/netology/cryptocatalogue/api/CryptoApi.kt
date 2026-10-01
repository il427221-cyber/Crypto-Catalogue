package ru.netology.cryptocatalogue.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.cryptocatalogue.dto.CryptoCoin
import ru.netology.cryptocatalogue.dto.CryptoCoinDetail
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://api.coinlore.net/api/"
private val okhttp = OkHttpClient.Builder()
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okhttp)
    .build()

interface CryptoApi {
    @GET("tickers/")
    suspend fun getCoinsList(
        @Query("start") start: Int = 0,
        @Query("limit") limit: Int = 20
    ): List<CryptoCoin>

    @GET("ticker/")
    suspend fun getCoinDetail(@Query("id") id:String): List<CryptoCoinDetail>
}

object ApiClient{
    val service: CryptoApi by lazy {
        retrofit.create(CryptoApi::class.java)
    }
}