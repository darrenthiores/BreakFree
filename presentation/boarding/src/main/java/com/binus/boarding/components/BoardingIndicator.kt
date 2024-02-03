package com.binus.boarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun BoardingIndicator(
    modifier: Modifier = Modifier,
    currentPage: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        (1..3).forEach { i ->
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(
                        if (i == currentPage) 20.dp else 10.dp
                    )
                    .clip(CircleShape)
                    .background(
                        if (i == currentPage) Color.White else Color.Gray
                    )
            )
        }
    }
}

@Preview
@Composable
private fun BoardingIndicatorPreview() {
    BreakFreeTheme {
        BoardingIndicator(currentPage = 2)
    }
}