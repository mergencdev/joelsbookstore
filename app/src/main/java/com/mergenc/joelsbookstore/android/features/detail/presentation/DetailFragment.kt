package com.mergenc.joelsbookstore.android.features.detail.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.mergenc.joelsbookstore.android.core.BaseFragment
import com.mergenc.joelsbookstore.databinding.FragmentDetailBinding

/**
 * Created by Mehmet Emin Ergenc on 3/8/2024
 */

class DetailFragment :
    BaseFragment<DetailViewModel, FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    override val viewModel: DetailViewModel by viewModels()

    private lateinit var bookImage: String
    private lateinit var title: String
    private lateinit var author: String
    private var rank: Int = 0
    private lateinit var description: String
    private lateinit var publisher: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetailArguments()
    }

    private fun getDetailArguments() {
        arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            bookImage = safeArgs.bookImage.toString()
            title = safeArgs.title.toString()
            author = safeArgs.author.toString()
            rank = safeArgs.rank
            description = safeArgs.description.toString()
            publisher = safeArgs.publisher.toString()
            Log.d("DetailFragment", "bookImage: $bookImage, title: $title, author: $author")

            binding.tvBookTitle.text = title
            binding.tvBookAuthor.text = author
            Glide.with(requireContext())
                .load(bookImage)
                .into(binding.ivBookImage)
            binding.tvRankValue.text = rank.toString()
            binding.tvDescriptionValue.text = description
            binding.tvPublisherValue.text = publisher
        }
    }
}