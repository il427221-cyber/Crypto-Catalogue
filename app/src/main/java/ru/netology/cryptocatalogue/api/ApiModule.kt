package ru.netology.cryptocatalogue.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.netology.cryptocatalogue.BuildConfig
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    companion object {
        private const val BASE_URL = "https://api.coinlore.net/api/"
    }

@Singleton
@Provides
fun provideLogging(): HttpLoggingInterceptor  {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if(BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }

    return interceptor
}

@Singleton
@Provides
fun provideOkhttp(
    logging: HttpLoggingInterceptor,
): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .addInterceptor(logging)
    .build()

@Singleton
@Provides
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

@Singleton
@Provides
fun provideApiClient(retrofit: Retrofit): CryptoApi = retrofit.create(CryptoApi::class.java)

}