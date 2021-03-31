package com.abdulmughni.personal.myreads.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
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