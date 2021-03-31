package com.abdulmughni.personal.myreads.di

import com.abdulmughni.personal.myreads.core.domain.usecase.BookInteractor
import com.abdulmughni.personal.myreads.core.domain.usecase.BookUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideBookUseCase(bookInteractor: BookInteractor): BookUseCase

}