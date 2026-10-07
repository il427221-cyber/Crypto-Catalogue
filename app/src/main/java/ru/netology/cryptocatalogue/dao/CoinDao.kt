package ru.netology.cryptocatalogue.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.netology.cryptocatalogue.entity.CoinEntity

@Dao
interface CoinDao {
    @Query("SELECT * FROM coinBase ORDER BY id DESC")
    fun getCoinsList(): Flow<List<CoinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: List<CoinEntity>)
}