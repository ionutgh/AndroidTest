package com.android.androidtestassignment.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.androidtestassignment.api.PAGE_SIZE
import com.android.androidtestassignment.api.UsersPagingSource
import com.android.androidtestassignment.api.UsersRetrofitApi
import com.android.androidtestassignment.api.datamodel.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersRetrofitApi: UsersRetrofitApi
): UsersRepository {
    override fun getUsers(): Flow<PagingData<UserModel>>{
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 3, initialLoadSize = PAGE_SIZE),
            pagingSourceFactory = {
                UsersPagingSource(usersRetrofitApi)
            }
        ).flow
    }
}