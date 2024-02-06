package com.binus.core_ui.components.headers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.onBackground,
    onBackClicked: () -> Unit
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        contentPadding = PaddingValues(horizontal = 24.dp),
        elevation = 0.dp
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.high
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .then(
                            Modifier
                                .size(24.dp)
                        )
                        .align(Alignment.CenterStart),
                    onClick = onBackClicked
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "back"
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .align(Alignment.Center),
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommonTopBarPreview() {
    BreakFreeTheme {
        CommonTopBar(title = "Feed") {

        }
    }
}