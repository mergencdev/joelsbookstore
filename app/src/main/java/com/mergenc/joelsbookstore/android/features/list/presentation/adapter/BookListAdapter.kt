package com.mergenc.joelsbookstore.android.features.list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mergenc.joelsbookstore.R
import com.mergenc.joelsbookstore.android.core.local.AppDatabase
import com.mergenc.joelsbookstore.android.core.local.room.model.FavoriteBooks
import com.mergenc.joelsbookstore.android.features.list.data.dto.Book
import com.mergenc.joelsbookstore.databinding.ItemBookBinding

/**
 * Created by Mehmet Emin Ergenc on 3/8/2024
 */

class BookListAdapter(
    private val books: MutableList<Book?>?,
    private val listener: (Book) -> Unit
) :
    RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    private lateinit var db: AppDatabase

    class BookViewHolder(val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book, clickListener: (Book) -> Unit) {
            itemView.setOnClickListener { clickListener(book) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookViewHolder(
        ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        db = AppDatabase.getInstance(holder.itemView.context)
        val book = books?.get(position)

        if (db.favoriteDao().isBookFavorited(book!!.title!!)) {
            holder.binding.ibFavorite.setImageResource(R.drawable.baseline_bookmark_24)
        } else {
            holder.binding.ibFavorite.setImageResource(R.drawable.baseline_bookmark_border_24)
        }

        holder.bind(book, listener)
        holder.binding.tvBookTitle.text = book.title
        holder.binding.tvBookAuthor.text = book.author
        Glide.with(holder.itemView.context)
            .load(book.bookImage)
            .into(holder.binding.ivBookImage)

        holder.binding.ibFavorite.setOnClickListener {
            val favoriteBooks = FavoriteBooks(
                id = 0,
                title = book.title,
                author = book.author,
                bookImage = book.bookImage,
                description = book.description,
                publisher = book.publisher,
                rank = book.rank
            )

            if (db.favoriteDao().isBookFavorited(book.title!!)) {
                db.favoriteDao().deleteFavoriteBook(book.title)
                holder.binding.ibFavorite.setImageResource(R.drawable.baseline_bookmark_border_24)
            } else {
                db.favoriteDao().insertFavoriteBook(favoriteBooks)
                holder.binding.ibFavorite.setImageResource(R.drawable.baseline_bookmark_24)
            }
        }
    }

    override fun getItemCount(): Int = books?.size!!
}