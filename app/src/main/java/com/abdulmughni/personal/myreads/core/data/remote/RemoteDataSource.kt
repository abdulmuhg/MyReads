package com.abdulmughni.personal.myreads.core.data.remote

import android.util.Log
import com.abdulmughni.personal.myreads.core.data.remote.network.ApiResponse
import com.abdulmughni.personal.myreads.core.data.remote.response.BookResponse
import com.abdulmughni.personal.myreads.core.utils.JsonHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val jsonHelper: JsonHelper){
    suspend fun getReadBook(): Flow<ApiResponse<List<BookResponse>>> {
        return flow {
            try {
                val response = jsonHelper.loadData()
                emit(ApiResponse.Success(response))
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}