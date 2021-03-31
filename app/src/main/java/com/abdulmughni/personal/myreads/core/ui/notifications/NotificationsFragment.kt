package com.abdulmughni.personal.myreads.core.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.abdulmughni.personal.myreads.R
import com.abdulmughni.personal.myreads.core.data.Responses
import com.abdulmughni.personal.myreads.core.domain.model.Book
import com.abdulmughni.personal.myreads.core.ui.BookAdapter
import com.abdulmughni.personal.myreads.core.ui.BookViewModel
import com.abdulmughni.personal.myreads.databinding.FragmentDashboardBinding
import com.abdulmughni.personal.myreads.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private val viewModel: BookViewModel by viewModels()
    private val binding: FragmentNotificationsBinding by lazy {
        FragmentNotificationsBinding.inflate(layoutInflater)
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
        viewModel.readBook.observe(viewLifecycleOwner, {
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
        viewModel.setReadStatus(item, !item.isRead)
    }

    private fun currentReadClick(item: Book) {
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