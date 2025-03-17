package com.android.androidtestassignment.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun ZonedDateTime.getFormattedHourMinutes(): String {
    val formatter =  DateTimeFormatter.ofPattern("HH:mm").withLocale(Locale.getDefault())
    return this.format(formatter)
}