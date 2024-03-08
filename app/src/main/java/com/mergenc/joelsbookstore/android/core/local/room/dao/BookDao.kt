package com.mergenc.joelsbookstore.android.core.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mergenc.joelsbookstore.android.features.list.data.dto.Book

/**
 * Created by Mehmet Emin Ergenc on 3/8/2024
 */

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAllBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<Book>)
}