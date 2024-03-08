package com.mergenc.joelsbookstore.android.core.local.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Mehmet Emin Ergenc on 3/8/2024
 */

@Entity(tableName = "favorites")
data class FavoriteBooks(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String?,
    val author: String?,
    val bookImage: String?,
    val description: String?,
    val publisher: String?,
    val rank: Int?
)
