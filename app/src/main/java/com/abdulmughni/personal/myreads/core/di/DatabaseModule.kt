package com.abdulmughni.personal.myreads.core.di

import android.content.Context
import androidx.room.Room
import com.abdulmughni.personal.myreads.core.data.local.room.BookDao
import com.abdulmughni.personal.myreads.core.data.local.room.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BookDatabase =
        Room.databaseBuilder(
            context,
            BookDatabase::class.java,
            "Book.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: BookDatabase): BookDao = database.bookDao()
}