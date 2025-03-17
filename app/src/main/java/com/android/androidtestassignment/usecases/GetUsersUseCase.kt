package com.android.androidtestassignment.usecases

import com.android.androidtestassignment.api.datamodel.UsersResponse
import com.android.androidtestassignment.repositories.UsersRepository
import com.android.androidtestassignment.ui.datamodels.User
import kotlinx.coroutines.Dispatchers
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): List<User> {
        val users = with(Dispatchers.IO) {
            usersRepository.getUsers(1, 20)
        }
        return users.results.map {
            User(
                id = it.login.uuid,
                name = "${it.name.first} ${it.name.last}",
                country = it.nat,
                profilePictureUrl = it.picture.thumbnail,
                age = it.dob.age,
                time = ZonedDateTime.parse(it.registered.date)
                    .withZoneSameInstant(ZoneId.systemDefault())
            )
        }
    }
}