package com.mergenc.joelsbookstore.android.features.favorites.adapter

import android.annotation.SuppressLint
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
 * Created by Mehmet Emin Ergenc on 3/9/2024
 */

class FavoritesAdapter(
    private val favorites: List<FavoriteBooks>,
    private val listener: (FavoriteBooks) -> Unit
) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private lateinit var db: AppDatabase

    class FavoritesViewHolder(val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteBooks: FavoriteBooks, clickListener: (FavoriteBooks) -> Unit) {
            itemView.setOnClickListener { clickListener(favoriteBooks) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavoritesViewHolder(
        ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        db = AppDatabase.getInstance(holder.itemView.context)
        val favoritedBook = favorites.get(position)

        if (db.favoriteDao().isBookFavorited(favoritedBook!!.title!!)) {
            holder.binding.ibFavorite.setImageResource(R.drawable.baseline_bookmark_24)
        } else {
            holder.binding.ibFavorite.setImageResource(R.drawable.baseline_bookmark_border_24)
        }

        holder.bind(favoritedBook, listener)
        holder.binding.tvBookTitle.text = favoritedBook.title
        holder.binding.tvBookAuthor.text = favoritedBook.author
        Glide.with(holder.itemView.context)
            .load(favoritedBook.bookImage)
            .into(holder.binding.ivBookImage)

        holder.binding.ibFavorite.setOnClickListener {
            db.favoriteDao().deleteFavoriteBook(favoritedBook.title!!)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = favorites.size
}