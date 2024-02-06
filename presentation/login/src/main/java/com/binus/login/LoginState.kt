package com.binus.login

data class LoginState(
    val showPassword: Boolean = false,
    val showDropDown: Boolean = false,
    val identifier: String = "+62",
    val isLoading: Boolean = false,
    val error: String? = null
)
