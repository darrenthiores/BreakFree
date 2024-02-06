package com.binus.register.model

import com.binus.register.R

enum class Gender {
    Male,
    Female;

    companion object {
        fun Gender.getImageId(): Int {
            return when (this) {
                Male -> R.drawable.bf_male
                Female -> R.drawable.bf_female
            }
        }
    }
}