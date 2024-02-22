package com.binus.register

import com.binus.register.model.Gender
import com.binus.register.model.RegisterSection
import kotlinx.datetime.LocalDateTime

sealed interface RegisterEvent {
    data class OnSelectGender(val gender: Gender): RegisterEvent
    data class OnNameChange(val name: String): RegisterEvent
    data class OnSelectBirthday(val birthday: LocalDateTime): RegisterEvent
    data class OnSelectIdentifier(
        val identifier: String
    ): RegisterEvent
    data class OnNumberChange(val number: String): RegisterEvent
    data class OnPasswordChange(val password: String): RegisterEvent
    data object OnToggleDropDown: RegisterEvent
    data object OnToggleShowPassword: RegisterEvent
    data class Next(val section: RegisterSection): RegisterEvent
    // otp
    data class OnOtpChange(val otp: String): RegisterEvent
    data object OnResend: RegisterEvent
    data object OnVerify: RegisterEvent
}