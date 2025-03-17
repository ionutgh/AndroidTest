package com.android.androidtestassignment.api

import com.android.androidtestassignment.api.datamodel.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersRetrofitApi {
    @GET("api")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String,
    ): UsersResponse
}