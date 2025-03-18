package com.android.androidtestassignment.usecases

import androidx.paging.PagingData
import com.android.androidtestassignment.ui.datamodels.User
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {
    operator fun invoke(): Flow<PagingData<User>>
}