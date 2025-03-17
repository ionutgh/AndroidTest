package com.android.androidtestassignment.repositories

import com.android.androidtestassignment.api.UsersRetrofitApi
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersRetrofitApi: UsersRetrofitApi
) {
    suspend fun getUsers(page: Int, results: Int) =
        usersRetrofitApi.getUsers(page, results, "abc")

}