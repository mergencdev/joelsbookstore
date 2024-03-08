package com.mergenc.joelsbookstore.android.core

import android.app.Application
import androidx.room.Room
import com.mergenc.joelsbookstore.android.core.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {
    private lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        initDatabase()
    }

    private fun initDatabase() {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "books_database"
        ).build()

        //this.deleteDatabase("books_database")
    }
}