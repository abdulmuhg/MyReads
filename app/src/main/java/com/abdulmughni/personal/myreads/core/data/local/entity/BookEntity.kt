package com.abdulmughni.personal.myreads.core.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val id: Int,

    var title: String,
    var author: String,
    var overview: String,
    var category: String,
    var rating: Double,
    var releaseDate: String,
    var poster: String,
    var isCurrentlyReading: Boolean,
    var isRead: Boolean,
    var isWantToRead: Boolean
) : Parcelable