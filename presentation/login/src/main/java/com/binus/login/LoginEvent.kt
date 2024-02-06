package com.binus.login

sealed interface LoginEvent {
    data object TogglePassword: LoginEvent
    data object ToggleDropDown: LoginEvent
    data class OnNumberChange(
        val number: String
    ): LoginEvent
    data class OnPasswordChange(
        val password: String
    ): LoginEvent
    data class OnSelectIdentifier(
        val identifier: String
    ): LoginEvent
    data object Login: LoginEvent
}