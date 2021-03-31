package com.abdulmughni.personal.myreads.core.domain.repository

import com.abdulmughni.personal.myreads.core.data.Responses
import com.abdulmughni.personal.myreads.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface IBookRepository {
    fun getWantToReadBook(): Flow<Responses<List<Book>>>
    fun getCurrentlyRead(): Flow<Responses<List<Book>>>
    fun getReadBook(): Flow<Responses<List<Book>>>

    fun updateReadStatus(book: Book, status: Boolean)
    fun updateCurrentReadStatus(book: Book, status: Boolean)
}