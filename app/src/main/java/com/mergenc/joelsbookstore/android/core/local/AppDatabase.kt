package com.mergenc.joelsbookstore.android.core.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mergenc.joelsbookstore.android.core.local.room.dao.BookDao
import com.mergenc.joelsbookstore.android.core.local.room.dao.FavoriteDao
import com.mergenc.joelsbookstore.android.core.local.room.model.FavoriteBooks
import com.mergenc.joelsbookstore.android.features.list.data.dto.Book

/**
 * Created by Mehmet Emin Ergenc on 3/8/2024
 */

@Database(entities = [Book::class, FavoriteBooks::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private const val DATABASE_NAME = "books_database"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}