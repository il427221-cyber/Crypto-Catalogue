package ru.netology.cryptocatalogue.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.cryptocatalogue.dto.CoinListResponse
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://api.coinlore.net/api/"

val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

private val okhttp = OkHttpClient.Builder()
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .addInterceptor(logging)
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
    ): CoinListResponse


    @GET("ticker/")
    suspend fun getCoinDetail(@Query("id") id:String): CoinListResponse
}

object ApiClient{
    val service: CryptoApi by lazy {
        retrofit.create(CryptoApi::class.java)
    }
}