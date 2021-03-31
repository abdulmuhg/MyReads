package com.abdulmughni.personal.myreads.core.domain.usecase

import com.abdulmughni.personal.myreads.core.data.Responses
import com.abdulmughni.personal.myreads.core.domain.model.Book
import com.abdulmughni.personal.myreads.core.domain.repository.IBookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookInteractor @Inject constructor(private val bookRepository: IBookRepository): BookUseCase {
    override fun getWantToReadBook(): Flow<Responses<List<Book>>> = bookRepository.getWantToReadBook()

    override fun getCurrentlyRead(): Flow<Responses<List<Book>>> = bookRepository.getCurrentlyRead()

    override fun getReadBook(): Flow<Responses<List<Book>>> = bookRepository.getReadBook()

    override fun updateReadStatus(book: Book, status: Boolean) = bookRepository.updateReadStatus(book, status)

    override fun updateCurrentReadStatus(book: Book, status: Boolean) = bookRepository.updateCurrentReadStatus(book, status)

}