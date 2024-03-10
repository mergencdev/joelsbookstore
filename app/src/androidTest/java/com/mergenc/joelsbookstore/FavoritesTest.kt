package com.mergenc.joelsbookstore

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mergenc.joelsbookstore.android.core.local.AppDatabase
import com.mergenc.joelsbookstore.android.core.local.room.dao.FavoriteDao
import com.mergenc.joelsbookstore.android.core.local.room.model.FavoriteBooks
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

/**
 * Created by Mehmet Emin Ergenc on 3/10/2024
 */

@RunWith(AndroidJUnit4::class)
class FavoritesTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: FavoriteDao

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = database.favoriteDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndRetrieveFavoriteBook() = runBlocking {
        val book = FavoriteBooks(
            id = 33,
            title = "Test Title 33",
            author = "Test Author 33",
            bookImage = "Test Image 33",
            description = "Test Description 33",
            publisher = "Test Publisher 33",
            rank = 33
        )
        dao.insertFavoriteBook(book)

        val retrievedBook = dao.getFavoriteBooks()
        assertTrue(retrievedBook.isNotEmpty())
        assertEquals(book.id, retrievedBook[0].id)
    }

    @Test
    fun deleteFavoriteBook() = runBlocking {
        val book = FavoriteBooks(
            id = 1,
            title = "Test Title",
            author = "Test Author",
            bookImage = "Test Image",
            description = "Test Description",
            publisher = "Test Publisher",
            rank = 1
        )

        val bookTitle = book.title
        dao.insertFavoriteBook(book)

        if (bookTitle != null) {
            dao.deleteFavoriteBook(bookTitle)
        }

        val result = dao.getFavoriteBooks()
        assertTrue(result.isEmpty())
    }
}