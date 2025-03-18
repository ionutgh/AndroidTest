package com.android.androidtestassignment.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.android.androidtestassignment.repositories.UsersRepository
import com.android.androidtestassignment.ui.datamodels.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
): GetUsersUseCase {
    override operator fun invoke(): Flow<PagingData<User>> {
        return usersRepository.getUsers().map { pagingData ->
            pagingData.map { userModel ->
                User(
                    id = userModel.login.uuid,
                    name = "${userModel.name.first} ${userModel.name.last}",
                    country = userModel.nat,
                    profilePictureUrl = userModel.picture.thumbnail,
                    age = userModel.dob.age,
                    time = ZonedDateTime.parse(userModel.registered.date)
                        .withZoneSameInstant(ZoneId.systemDefault())
                )
            }
        }
    }
}