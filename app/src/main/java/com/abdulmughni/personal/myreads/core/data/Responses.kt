package com.abdulmughni.personal.myreads.core.data

sealed class Responses<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Responses<T>(data)
    class Loading<T>(data: T? = null) : Responses<T>(data)
    class Error<T>(message: String, data: T? = null) : Responses<T>(data, message)
}