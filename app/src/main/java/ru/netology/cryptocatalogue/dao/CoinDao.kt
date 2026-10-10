package ru.netology.cryptocatalogue.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.cryptocatalogue.entity.CoinDetailEntity
import ru.netology.cryptocatalogue.entity.CoinEntity

@Dao
interface CoinDao {

    @Query("SELECT * FROM coinBase ORDER BY id")
    suspend fun getCoinsOnce(): List<CoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(coins: List<CoinEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coin: CoinDetailEntity)

    @Query("SELECT * FROM coinDetails WHERE id = :id")
    suspend fun getCoinDetailById(id: String): CoinDetailEntity?
}