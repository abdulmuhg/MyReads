package com.abdulmughni.personal.myreads.core.data

import com.abdulmughni.personal.myreads.core.data.local.LocalDataSource
import com.abdulmughni.personal.myreads.core.data.local.entity.BookEntity
import com.abdulmughni.personal.myreads.core.data.remote.RemoteDataSource
import com.abdulmughni.personal.myreads.core.data.remote.network.ApiResponse
import com.abdulmughni.personal.myreads.core.data.remote.response.BookResponse
import com.abdulmughni.personal.myreads.core.domain.model.Book
import com.abdulmughni.personal.myreads.core.domain.repository.IBookRepository
import com.abdulmughni.personal.myreads.core.utils.AppExecutors
import com.abdulmughni.personal.myreads.core.utils.BookMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IBookRepository {
    override fun getWantToReadBook(): Flow<Responses<List<Book>>> =
        object : NetworkBoundResource<List<Book>, List<BookResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Book>> =
                localDataSource.getWantToRead().map {
                    BookMapper.mapEntityToDomain(it)
                }

            override fun shouldFetch(data: List<Book>?): Boolean = data!= null

            override suspend fun saveCallResult(data: List<BookResponse>) {
                val movieList = BookMapper.mapResponsesToEntities(data)
                localDataSource.insertBook(movieList)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<BookResponse>>> =
                remoteDataSource.getReadBook()

        }.asFlow()

    override fun getCurrentlyRead(): Flow<Responses<List<Book>>> =
        object : NetworkBoundResource<List<Book>, List<BookEntity>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Book>> =
                localDataSource.getCurrentlyRead().map {
                    BookMapper.mapEntityToDomain(it)
                }

            override fun shouldFetch(data: List<Book>?): Boolean = false

            override suspend fun createCall(): Flow<ApiResponse<List<BookEntity>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<BookEntity>) {
                TODO("Not yet implemented")
            }

        }.asFlow()


    override fun getReadBook(): Flow<Responses<List<Book>>> =
        object : NetworkBoundResource<List<Book>, List<BookEntity>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Book>> =
                localDataSource.getReadBook().map {
                    BookMapper.mapEntityToDomain(it)
                }

            override fun shouldFetch(data: List<Book>?): Boolean = false

            override suspend fun createCall(): Flow<ApiResponse<List<BookEntity>>> {
                TODO("Not yet implemented")
            }

            override suspend fun saveCallResult(data: List<BookEntity>) {
                TODO("Not yet implemented")
            }

        }.asFlow()

    override fun updateReadStatus(book: Book, status: Boolean) {
        val bookEntity = BookMapper.mapDomainToEntity(book)
        appExecutors.diskIO().execute {
            localDataSource.updateReadStatus(bookEntity, status)
        }
    }

    override fun updateCurrentReadStatus(book: Book, status: Boolean) {
        val bookEntity = BookMapper.mapDomainToEntity(book)
        appExecutors.diskIO().execute {
            localDataSource.updateCurrentReadStatus(bookEntity, status)
        }
    }

}