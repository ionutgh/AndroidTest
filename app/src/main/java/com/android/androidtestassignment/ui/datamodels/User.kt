package com.android.androidtestassignment.ui.datamodels

import java.time.ZonedDateTime

data class User(
    val id: String,
    val name: String,
    val country: String,
    val profilePictureUrl: String,
    val age: Int,
    val time: ZonedDateTime,
)
