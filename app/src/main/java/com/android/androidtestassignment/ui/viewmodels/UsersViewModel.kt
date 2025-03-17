package com.android.androidtestassignment.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.androidtestassignment.ui.datamodels.User
import com.android.androidtestassignment.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _usersStateFlow = MutableStateFlow<List<User>>(emptyList())
    val usersStateFlow: StateFlow<List<User>> = _usersStateFlow

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch{
            try {
                _usersStateFlow.value = getUsersUseCase()
            } catch (e: Exception) {

            }
        }
    }
}