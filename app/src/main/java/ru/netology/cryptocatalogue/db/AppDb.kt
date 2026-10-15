package ru.netology.cryptocatalogue.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.netology.cryptocatalogue.dao.CoinDao
import ru.netology.cryptocatalogue.entity.CoinDetailEntity
import ru.netology.cryptocatalogue.entity.CoinEntity


@Database(entities = [CoinEntity::class, CoinDetailEntity::class], version = 2, exportSchema = false)
abstract class AppDb: RoomDatabase() {
    abstract fun coinDao(): CoinDao
}
