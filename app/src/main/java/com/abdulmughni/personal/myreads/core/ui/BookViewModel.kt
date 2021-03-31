package com.abdulmughni.personal.myreads.core.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abdulmughni.personal.myreads.core.domain.model.Book
import com.abdulmughni.personal.myreads.core.domain.usecase.BookUseCase

class BookViewModel @ViewModelInject constructor(private val bookUseCase: BookUseCase): ViewModel() {
    var wantToRead = bookUseCase.getWantToReadBook().asLiveData()
    var readBook = bookUseCase.getReadBook().asLiveData()
    var currentRead = bookUseCase.getCurrentlyRead().asLiveData()

    fun setReadStatus(book: Book, status: Boolean) = bookUseCase.updateReadStatus(book, status)
    fun setCurrentRead(book: Book, status: Boolean) = bookUseCase.updateCurrentReadStatus(book, status)
}