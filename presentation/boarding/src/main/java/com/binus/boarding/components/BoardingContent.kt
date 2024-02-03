package com.binus.boarding.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.boarding.R
import com.binus.core_ui.components.buttons.DefaultButton
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun BoardingContent(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
    title: String,
    description: String,
    currentPage: Int,
    onNextClick: () -> Unit,
    onStartClick: () -> Unit
) {
    val painter = painterResource(id = imageId)

    Column(
        modifier = modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(
                horizontal = 24.dp
            )
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(56.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))

            if (currentPage < 3) {
                DefaultButton(
                    label = stringResource(id = R.string.skip),
                    onClick = onStartClick,
                    color = MaterialTheme.colors.primaryVariant,
                    textColor = MaterialTheme.colors.primary
                )
            }
        }

        Image(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
                .aspectRatio(
                    ratio = painter.intrinsicSize.height / painter.intrinsicSize.width
                ),
            painter = painter,
            contentDescription = stringResource(id = R.string.boarding_cd, currentPage),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier
                .weight(1f)
                .heightIn(
                    min = 32.dp
                )
        )

        BoardingCard(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                ),
            title = title,
            description = description,
            currentPage = currentPage
        )

        Spacer(modifier = Modifier.height(24.dp))

        when (currentPage) {
            3 -> {
                DefaultButton(
                    modifier = Modifier
                        .align(
                            Alignment.End
                        ),
                    label = stringResource(id = R.string.get_started),
                    onClick = onStartClick,
                    color = MaterialTheme.colors.primaryVariant,
                    textColor = MaterialTheme.colors.primary
                )
            }
            else -> {
                IconButton(
                    modifier = Modifier
                        .align(
                            Alignment.End
                        ),
                    onClick = onNextClick
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = stringResource(id = R.string.next),
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun BoardingContentPreview() {
    BreakFreeTheme {
        BoardingContent(
            imageId = R.drawable.bf_boarding_1,
            title = stringResource(id = R.string.boarding_1_title),
            description = stringResource(id = R.string.boarding_1_desc),
            currentPage = 3,
            onNextClick = {  },
            onStartClick = {  }
        )
    }
}