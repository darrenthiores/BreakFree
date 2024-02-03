package com.binus.boarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.boarding.R
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun BoardingCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    currentPage: Int
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(30.dp)
            )
            .fillMaxWidth()
            .padding(
                horizontal = 64.dp,
                vertical = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5.copy(
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.subtitle1.copy(
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        BoardingIndicator(currentPage = currentPage)
    }
}

@Preview
@Composable
private fun BoardingCardPreview() {
    BreakFreeTheme {
        BoardingCard(
            title = stringResource(id = R.string.boarding_1_title),
            description = stringResource(id = R.string.boarding_1_desc),
            currentPage = 1
        )
    }
}