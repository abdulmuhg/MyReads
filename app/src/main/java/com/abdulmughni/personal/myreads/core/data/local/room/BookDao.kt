package com.abdulmughni.personal.myreads.core.data.local.room

import androidx.room.*
import com.abdulmughni.personal.myreads.core.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books WHERE isRead = 1")
    fun getIsRead(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isCurrentlyReading = 1")
    fun getCurrentlyRead(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE isWantToRead = 1")
    fun getWantToRead(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBooks(bookList: List<BookEntity>)

    @Update
    fun updateBookStatus(book: BookEntity)
}