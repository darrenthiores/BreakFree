package com.binus.core_ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun SelectableButton(
    modifier: Modifier = Modifier,
    label: String?,
    onClick: () -> Unit,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    disabled: Boolean = false,
    color: Color = MaterialTheme.colors.primary,
    textColor: Color? = null,
    iconColor: Color? = null,
    size: ButtonSize = ButtonSize.Medium,
    shape: ButtonShape = ButtonShape.Rounded,
    type: ButtonType = ButtonType.Fill,
    isLoading: Boolean = false
) {
    val buttonHeight = when(size) {
        ButtonSize.Small -> 24.dp
        ButtonSize.Medium -> 48.dp
        ButtonSize.Large -> 56.dp
    }

    val textStyle = when(size) {
        ButtonSize.Small -> MaterialTheme.typography.button
        ButtonSize.Medium -> MaterialTheme.typography.subtitle1
        ButtonSize.Large -> MaterialTheme.typography.subtitle1
    }

    val iconSize = when(size) {
        ButtonSize.Large -> 24.dp
        ButtonSize.Medium -> 20.dp
        ButtonSize.Small -> 16.dp
    }

    val cornerRadius = when(shape) {
        ButtonShape.Rounded -> {
            when(size) {
                ButtonSize.Small -> 8.dp
                ButtonSize.Medium -> 12.dp
                ButtonSize.Large -> 12.dp
            }
        }
        ButtonShape.Pill -> {
            when(size) {
                ButtonSize.Small -> 24.dp
                ButtonSize.Medium -> 48.dp
                ButtonSize.Large -> 56.dp
            }
        }
    }

    val bgColor = when(type) {
        ButtonType.Fill -> color
        ButtonType.NoFill -> MaterialTheme.colors.background
        is ButtonType.Outline -> type.background ?: MaterialTheme.colors.background
    }

    val contentColor = textColor ?: when(type) {
        ButtonType.Fill -> MaterialTheme.colors.onPrimary
        ButtonType.NoFill -> color
        is ButtonType.Outline -> MaterialTheme.colors.onBackground
    }

    val disabledBgColor = when(type) {
        ButtonType.Fill -> Color.Gray
        ButtonType.NoFill -> MaterialTheme.colors.background
        is ButtonType.Outline -> MaterialTheme.colors.background
    }

    val disabledContentColor = when(type) {
        ButtonType.Fill -> MaterialTheme.colors.onPrimary
        ButtonType.NoFill -> Color.LightGray
        is ButtonType.Outline -> Color.LightGray
    }

    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = bgColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBgColor,
        disabledContentColor = disabledContentColor
    )

    val buttonBorder = when(type) {
        is ButtonType.Outline -> BorderStroke(
            width = 1.dp,
            color = color
        )
        else -> null
    }

    val paddingHorizontal = when(size) {
        ButtonSize.Small -> 12.dp
        ButtonSize.Medium -> 16.dp
        ButtonSize.Large -> 24.dp
    }
    val paddingVertical = 8.dp

    val elevation = when (type) {
        ButtonType.NoFill -> ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        )
        else -> null
    }

    Button(
        modifier = modifier
            .defaultMinSize(
                minHeight = buttonHeight + paddingVertical
            )
            .height(buttonHeight + paddingVertical),
        onClick = onClick,
        enabled = !disabled,
        shape = RoundedCornerShape(cornerRadius),
        border = buttonBorder,
        colors = buttonColors,
        contentPadding = PaddingValues(
            vertical = 0.dp,
            horizontal = paddingHorizontal
        ),
        elevation = elevation
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(iconSize),
                color = contentColor
            )
        } else {
            startIcon?.let {
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor ?: (if (disabled) Color.Gray else MaterialTheme.colors.onBackground)
                )

                label?.let {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }

            label?.let {
                Text(
                    text = it,
                    style = textStyle,
                    modifier = Modifier
                )
            }

            endIcon?.let {
                label?.let {
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor ?: (if (disabled) Color.Gray else MaterialTheme.colors.onBackground)
                )
            }
        }
    }
}

@Preview(
    name = "Medium Rounded Fill Button"
)
@Composable
private fun MediumRoundedFillButtonPreview() {
    BreakFreeTheme(
        darkTheme = true
    ) {
        SelectableButton(
            label = "Button",
            onClick = { }
        )
    }
}

@Preview(
    name = "Small Rounded Fill Button"
)
@Composable
private fun SmallRoundedFillButtonPreview() {
    BreakFreeTheme {
        SelectableButton(
            label = "Button",
            onClick = { },
            size = ButtonSize.Small
        )
    }
}

@Preview(
    name = "Medium Pill Outline Button"
)
@Composable
private fun MediumPillOutlineButtonPreview() {
    BreakFreeTheme {
        SelectableButton(
            label = "Button",
            onClick = { },
            shape = ButtonShape.Pill,
            type = ButtonType.Outline()
        )
    }
}

@Preview(
    name = "Medium Rounded Fill Width Button"
)
@Composable
private fun MediumRoundedFillWidthButtonPreview() {
    BreakFreeTheme {
        SelectableButton(
            modifier = Modifier.fillMaxWidth(),
            label = "Button",
            onClick = { },
            shape = ButtonShape.Rounded,
            type = ButtonType.Fill
        )
    }
}

@Preview(
    name = "LoadingButton"
)
@Composable
private fun LoadingButtonPreview() {
    BreakFreeTheme {
        SelectableButton(
            modifier = Modifier.fillMaxWidth(),
            label = "Button",
            onClick = { },
            shape = ButtonShape.Rounded,
            type = ButtonType.Fill,
            isLoading = true
        )
    }
}