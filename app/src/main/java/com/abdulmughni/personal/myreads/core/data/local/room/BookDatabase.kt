package com.abdulmughni.personal.myreads.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdulmughni.personal.myreads.core.data.local.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}