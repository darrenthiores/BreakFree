package com.binus.register

import com.binus.register.model.Gender
import com.binus.register.model.RegisterSection
import kotlinx.datetime.LocalDateTime

data class RegisterState(
    val currentSection: RegisterSection = RegisterSection.Personal,
    val gender: Gender? = null,
    val birthday: LocalDateTime? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
