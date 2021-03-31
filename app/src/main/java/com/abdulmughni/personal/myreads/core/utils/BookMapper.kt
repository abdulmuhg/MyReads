package com.abdulmughni.personal.myreads.core.utils

import com.abdulmughni.personal.myreads.core.data.local.entity.BookEntity
import com.abdulmughni.personal.myreads.core.data.remote.response.BookResponse
import com.abdulmughni.personal.myreads.core.domain.model.Book

object BookMapper {

    fun mapResponsesToEntities(input: List<BookResponse>): List<BookEntity>{
        val data = ArrayList<BookEntity>()
        input.map {
            val book = BookEntity(
                id = it.id,
                title = it.title,
                author = it.author,
                overview = it.overview,
                category = it.category,
                rating = it.rating,
                releaseDate = it.releaseDate,
                poster = it.poster,
                isCurrentlyReading = it.isCurrentlyReading,
                isRead = it.isRead,
                isWantToRead = it.isWantToRead
            )
            data.add(book)
        }
        return data
    }

    fun mapEntityToDomain(input: List<BookEntity>): List<Book> =
        input.map {
            Book(
                id = it.id,
                title = it.title,
                author = it.author,
                overview = it.overview,
                category = it.category,
                rating = it.rating,
                releaseDate = it.releaseDate,
                poster = it.poster,
                isCurrentlyReading = it.isCurrentlyReading,
                isRead = it.isRead,
                isWantToRead = it.isWantToRead
            )
        }

    fun mapEntitiesToDomain(it: BookEntity): Book =
            Book(
                id = it.id,
                title = it.title,
                author = it.author,
                overview = it.overview,
                category = it.category,
                rating = it.rating,
                releaseDate = it.releaseDate,
                poster = it.poster,
                isCurrentlyReading = it.isCurrentlyReading,
                isRead = it.isRead,
                isWantToRead = it.isWantToRead
            )



    fun mapDomainToEntity(it: Book) = BookEntity(
        id = it.id,
        title = it.title,
        author = it.author,
        overview = it.overview,
        category = it.category,
        rating = it.rating,
        releaseDate = it.releaseDate,
        poster = it.poster,
        isCurrentlyReading = it.isCurrentlyReading,
        isRead = it.isRead,
        isWantToRead = it.isWantToRead
    )
}