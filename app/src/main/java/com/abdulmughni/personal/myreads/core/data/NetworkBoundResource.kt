package com.abdulmughni.personal.myreads.core.data

import com.abdulmughni.personal.myreads.core.data.remote.network.ApiResponse
import com.abdulmughni.personal.myreads.core.utils.AppExecutors
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private var result: Flow<Responses<ResultType>> = flow {
        emit(Responses.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Responses.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Responses.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Responses.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Responses.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDB().map { Responses.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Responses<ResultType>> = result
}