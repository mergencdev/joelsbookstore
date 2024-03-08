package com.mergenc.joelsbookstore.android.core.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mergenc.joelsbookstore.android.core.local.room.model.FavoriteBooks
import com.mergenc.joelsbookstore.android.features.list.data.dto.Book

/**
 * Created by Mehmet Emin Ergenc on 3/8/2024
 */

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    fun getFavoriteBooks(): List<FavoriteBooks>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE title = :title)")
    fun isBookFavorited(title: String): Boolean

    @Insert
    fun insertFavoriteBook(favoriteBooks: FavoriteBooks)

    @Query("DELETE FROM favorites WHERE title = :title")
    fun deleteFavoriteBook(title: String)
}