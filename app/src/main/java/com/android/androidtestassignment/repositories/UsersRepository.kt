package com.android.androidtestassignment.repositories

import androidx.paging.PagingData
import com.android.androidtestassignment.api.datamodel.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsers(): Flow<PagingData<UserModel>>
}