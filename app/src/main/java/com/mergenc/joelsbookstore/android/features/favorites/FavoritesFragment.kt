package com.mergenc.joelsbookstore.android.features.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mergenc.joelsbookstore.android.core.BaseFragment
import com.mergenc.joelsbookstore.android.core.local.AppDatabase
import com.mergenc.joelsbookstore.android.features.favorites.adapter.FavoritesAdapter
import com.mergenc.joelsbookstore.databinding.FragmentFavoritesBinding

class FavoritesFragment :
    BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {
    override val viewModel: FavoritesViewModel by viewModels()

    private lateinit var favoritesAdapter: FavoritesAdapter
    private lateinit var db: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(requireContext())

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val favorites = db.favoriteDao().getFavoriteBooks()
        favoritesAdapter = FavoritesAdapter(favorites) {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(
                it.bookImage,
                it.title,
                it.author,
                it.rank!!,
                it.description,
                it.publisher
            )
            findNavController().navigate(action)
        }

        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvFavorites.layoutManager = layoutManager
        binding.rvFavorites.adapter = favoritesAdapter
    }
}