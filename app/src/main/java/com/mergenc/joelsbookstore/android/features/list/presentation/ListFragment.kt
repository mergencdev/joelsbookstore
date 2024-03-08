package com.mergenc.joelsbookstore.android.features.list.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mergenc.androidcore.domain.UiError
import com.mergenc.joelsbookstore.android.core.ApiErrorModel
import com.mergenc.joelsbookstore.android.core.BaseFragment
import com.mergenc.joelsbookstore.android.core.local.AppDatabase
import com.mergenc.joelsbookstore.android.features.list.data.dto.Book
import com.mergenc.joelsbookstore.android.features.list.domain.ListApiState
import com.mergenc.joelsbookstore.android.features.list.domain.ListUiModel
import com.mergenc.joelsbookstore.android.features.list.presentation.adapter.BookListAdapter
import com.mergenc.joelsbookstore.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mehmet Emin Ergenc on 3/7/2024
 */

@AndroidEntryPoint

class ListFragment :
    BaseFragment<ListViewModel, FragmentListBinding>(FragmentListBinding::inflate) {

    private lateinit var bookListAdapter: BookListAdapter
    private lateinit var db: AppDatabase

    override val viewModel: ListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        collectPageState(viewModel.pageStateFlow) { pageState ->
            when (pageState.pageState) {
                ListViewModel.PageEvent.INITIAL -> {
                    viewModel.getList()
                }

                ListViewModel.PageEvent.LIST_RESPONSE_RECEIVED -> {
                    onListResponseReceived(pageState.homePageApiState)
                }
            }
        }
    }

    private fun onListResponseReceived(listApiState: ListApiState) {
        when (listApiState) {
            is ListApiState.Initial -> {
                // handle success
            }

            is ListApiState.Success -> {
                handleListPageSuccessResponse(listApiState.uiModel)
            }

            is ListApiState.Error -> {
                handleListPageErrorResponse(listApiState.error)
            }
        }
    }

    private fun handleListPageSuccessResponse(uiModel: ListUiModel?) {
        setRecyclerView(uiModel)
        saveBookListToDatabase(uiModel)
    }

    private fun handleListPageErrorResponse(error: UiError<ApiErrorModel>) {
        setRecyclerViewWithLocalData()
        handleNetworkError(error)
    }

    private fun setRecyclerViewWithLocalData() {
        if (db.bookDao().getAllBooks().isNotEmpty()) {
            val books = db.bookDao().getAllBooks()
            bookListAdapter = BookListAdapter(books.toMutableList(),
                listener = {
                    Log.d("ListFragment", "Book clicked: $it")
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                        it.bookImage,
                        it.title,
                        it.author,
                        it.rank!!,
                        it.description,
                        it.publisher
                    )
                    findNavController().navigate(action)
                }
            )

            val layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
            binding.rvBookList.layoutManager = layoutManager
            binding.rvBookList.adapter = bookListAdapter
        }
    }

    private fun setRecyclerView(uiModel: ListUiModel?) {
        val books = uiModel?.results?.books?.toMutableList()
        bookListAdapter = BookListAdapter(books,
            listener = {
                Log.d("ListFragment", "Book clicked: $it")
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                    it.bookImage,
                    it.title,
                    it.author,
                    it.rank!!,
                    it.description,
                    it.publisher
                )
                findNavController().navigate(action)
            }
        )

        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        binding.rvBookList.layoutManager = layoutManager
        binding.rvBookList.adapter = bookListAdapter
    }

    private fun saveBookListToDatabase(uiModel: ListUiModel?) {
        val books = uiModel?.results?.books!!
        db.bookDao().insertAll(books as List<Book>)
        Log.d("33: book", db.bookDao().getAllBooks().toString())
    }
}