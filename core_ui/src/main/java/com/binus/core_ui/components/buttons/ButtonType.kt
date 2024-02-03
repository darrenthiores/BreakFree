package com.binus.core_ui.components.buttons

import androidx.compose.ui.graphics.Color

sealed interface ButtonType {
    object Fill: ButtonType
    data class Outline(val background: Color? = null): ButtonType
    object NoFill: ButtonType
}