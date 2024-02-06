package com.binus.register

import com.binus.register.model.Gender
import com.binus.register.model.RegisterSection
import kotlinx.datetime.LocalDateTime

sealed interface RegisterEvent {
    data class OnSelectGender(val gender: Gender): RegisterEvent
    data class OnNameChange(val name: String): RegisterEvent
    data class OnSelectBirthday(val birthday: LocalDateTime): RegisterEvent
    data class Next(val section: RegisterSection): RegisterEvent
}