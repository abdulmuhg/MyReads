package com.abdulmughni.personal.myreads.core.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.abdulmughni.personal.myreads.core.data.Responses
import com.abdulmughni.personal.myreads.core.domain.model.Book
import com.abdulmughni.personal.myreads.core.ui.BookAdapter
import com.abdulmughni.personal.myreads.core.ui.BookViewModel
import com.abdulmughni.personal.myreads.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: BookViewModel by viewModels()
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val bookAdapter: BookAdapter by lazy {
        BookAdapter(
            { item -> readClick(item) },
            { item -> currentReadClick(item) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAdapter()
    }


    private fun setupViewModel() {
        viewModel.wantToRead.observe(viewLifecycleOwner, {
            when (it) {
                is Responses.Loading -> isLoading(true)
                is Responses.Success -> getDataSuccess(it)
            }
        })
    }

    private fun setupAdapter() {
        with(binding) {
            rv.also {
                it.adapter = bookAdapter
                it.layoutManager = GridLayoutManager(requireContext(), 1)
                it.setHasFixedSize(true)
            }
        }
    }

    private fun readClick(item: Book) {
        Toast.makeText(requireContext(), "Added to Read List", Toast.LENGTH_SHORT).show()
        viewModel.setReadStatus(item, !item.isRead)
    }

    private fun currentReadClick(item: Book) {
        Toast.makeText(requireContext(), "Added to Currently Read List", Toast.LENGTH_SHORT).show()
        viewModel.setCurrentRead(item, !item.isCurrentlyReading)
    }

    private fun getDataSuccess(movie: Responses<List<Book>>) {
        bookAdapter.setData(movie.data)
        isLoading(false)
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}