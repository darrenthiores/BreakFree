package com.binus.core_ui.utils

import com.binus.core_ui.R

object UiConstant {
    fun getFlagId(number: String): Int {
        return when (number) {
            "+62" -> R.drawable.flag
            else -> R.drawable.flag
        }
    }
}