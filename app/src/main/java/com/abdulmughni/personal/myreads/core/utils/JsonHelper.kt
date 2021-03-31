package com.abdulmughni.personal.myreads.core.utils

import android.content.Context
import com.abdulmughni.personal.myreads.R
import com.abdulmughni.personal.myreads.core.data.remote.response.BookResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.books).bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<BookResponse> {
        val list = ArrayList<BookResponse>()
        val responseObject = JSONObject(parsingFileToString().toString())
        val listArray = responseObject.getJSONArray("books")
        for (i in 0 until listArray.length()) {
            val book = listArray.getJSONObject(i)

            val id = book.getInt("id")
            val title = book.getString("title")
            val author = book.getString("author")
            val overview = book.getString("overview")
            val category = book.getString("category")
            val rating = book.getDouble("rating")
            val releaseDate = book.getString("releaseDate")
            val poster = book.getString("poster")
            val currentRead = book.getBoolean("isCurrentlyReading")
            val isRead = book.getBoolean("isRead")
            val isWantToRead = book.getBoolean("isWantToRead")

            val bookResponse = BookResponse(
                id = id,
                title = title,
                author = author,
                overview = overview,
                category = category,
                rating = rating,
                releaseDate = releaseDate,
                poster = poster,
                isCurrentlyReading = currentRead,
                isRead = isRead,
                isWantToRead = isWantToRead
            )
            list.add(bookResponse)
        }
        return list
    }
}

