package com.abdulmughni.personal.myreads.core.data.local

import com.abdulmughni.personal.myreads.core.data.local.entity.BookEntity
import com.abdulmughni.personal.myreads.core.data.local.room.BookDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val bookDao: BookDao) {
    fun getReadBook(): Flow<List<BookEntity>> = bookDao.getIsRead()
    fun getCurrentlyRead(): Flow<List<BookEntity>> = bookDao.getCurrentlyRead()
    fun getWantToRead(): Flow<List<BookEntity>> = bookDao.getWantToRead()

    suspend fun insertBook(bookList: List<BookEntity>) = bookDao.insertBooks(bookList)

    fun updateReadStatus(bookEntity: BookEntity, currentStatus: Boolean){
        bookEntity.isRead = currentStatus
        bookDao.updateBookStatus(bookEntity)
    }

    fun updateCurrentReadStatus(bookEntity: BookEntity, currentStatus: Boolean){
        bookEntity.isCurrentlyReading = currentStatus
        bookDao.updateBookStatus(bookEntity)
    }

    fun updateWantToReadStatus(bookEntity: BookEntity, currentStatus: Boolean){
        bookEntity.isWantToRead = currentStatus
        bookDao.updateBookStatus(bookEntity)
    }
}