package com.android.androidtestassignment.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.android.androidtestassignment.ui.datamodels.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor() : ViewModel() {
    private val _usersStateFlow = MutableStateFlow<List<User>>(emptyList())
    val usersStateFlow: StateFlow<List<User>> = _usersStateFlow

    init {
        getUsers()
    }

    private fun getUsers() {
        _usersStateFlow.value = getMockData()
    }

    private fun getMockData() = listOf(
        User(
            id = "1",
            name = "Laura Woods",
            country = "IE",
            profilePictureUrl = "https://randomuser.me/api/portraits/thumb/women/88.jpg",
            age = 25,
            time = ZonedDateTime.parse("2018-10-18T04:05:51.990Z")
                .withZoneSameInstant(ZoneId.systemDefault())
        ),
        User(
            id = "2",
            name = "Marten Faber",
            country = "DE",
            profilePictureUrl = "https://randomuser.me/api/portraits/thumb/men/1.jpg",
            age = 42,
            time = ZonedDateTime.parse("2002-04-03T08:57:47.321Z")
                .withZoneSameInstant(ZoneId.systemDefault())
        ),
        User(
            id = "3",
            name = "Christy Diaz",
            country = "AU",
            profilePictureUrl = "https://randomuser.me/api/portraits/thumb/women/53.jpg",
            age = 42,
            time = ZonedDateTime.parse("2021-11-29T02:24:48.253Z")
                .withZoneSameInstant(ZoneId.systemDefault())
        )
    )
}