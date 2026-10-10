package ru.netology.cryptocatalogue.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.cryptocatalogue.dao.CoinDao
import ru.netology.cryptocatalogue.entity.CoinDetailEntity
import ru.netology.cryptocatalogue.entity.CoinEntity


@Database(entities = [CoinEntity::class, CoinDetailEntity::class], version = 2, exportSchema = false)
abstract class AppDb: RoomDatabase() {
    abstract fun coinDao(): CoinDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        @Suppress("DEPRECATION")
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}