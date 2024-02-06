package com.binus.get_started

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.components.buttons.ButtonShape
import com.binus.core_ui.components.buttons.DefaultButton
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun GetStartedScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val painter = painterResource(id = R.drawable.bf_get_started)

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(
                horizontal = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Image(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .aspectRatio(
                    ratio = painter.intrinsicSize.height / painter.intrinsicSize.width
                ),
            painter = painter,
            contentDescription = stringResource(id = R.string.get_started_cd),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier
                .height(48.dp)
        )

        Text(
            text = stringResource(id = R.string.get_started_title),
            style = MaterialTheme.typography.h5.copy(
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = R.string.get_started_desc),
            style = MaterialTheme.typography.subtitle1.copy(
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center
            )
        )

        Spacer(
            modifier = Modifier
                .height(48.dp)
        )

        DefaultButton(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.register),
            onClick = onRegisterClick,
            shape = ButtonShape.Pill
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.have_acc) + " ",
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onBackground
                )
            )

            Text(
                modifier = Modifier
                    .clickable {
                        onLoginClick()
                    },
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.primary
                )
            )
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Preview
@Composable
private fun GetStartedScreenPreview() {
    BreakFreeTheme {
        GetStartedScreen(
            onRegisterClick = {  },
            onLoginClick = {  }
        )
    }
}