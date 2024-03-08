package com.mergenc.joelsbookstore.android.features.list.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mergenc.joelsbookstore.android.features.list.domain.ListUiModel

data class ListResponse(
    @SerializedName("results")
    val results: Results
)

data class Results(
    @SerializedName("books")
    val books: List<Book?>?,
    @SerializedName("published_date")
    val publishedDate: String?
)

@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    val id: Int?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("book_image")
    val bookImage: String?,
    @SerializedName("book_image_height")
    val bookImageHeight: Int?,
    @SerializedName("book_image_width")
    val bookImageWidth: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("description")
    val description: String?,
    var isFavorite: Int = 0
)

fun ListResponse.toDomain(): ListUiModel {

    return ListUiModel(
        this.results
    )
}