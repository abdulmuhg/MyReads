package com.abdulmughni.personal.myreads.core.di

import com.abdulmughni.personal.myreads.core.data.Repository
import com.abdulmughni.personal.myreads.core.domain.repository.IBookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(bookRepository: Repository): IBookRepository
}