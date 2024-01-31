package com.binus.core_ui.utils

sealed class UiEvent {
    data class Success(
        val data: String? = null
    ): UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackBar(
        val message: String
    ): UiEvent()
}
