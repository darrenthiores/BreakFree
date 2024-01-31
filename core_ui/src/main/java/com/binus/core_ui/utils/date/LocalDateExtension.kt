package com.binus.core_ui.utils.date

import kotlinx.datetime.LocalDate

fun LocalDate.asString(): String {
    val month = if (monthNumber < 10) "0$monthNumber" else monthNumber
    val day = if(dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth

    return "$day/$month/$year"
}