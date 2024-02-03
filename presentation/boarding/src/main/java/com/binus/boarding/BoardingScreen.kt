package com.binus.boarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.binus.boarding.components.BoardingContent

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoardingScreen(
    state: BoardingState,
    onEvent: (BoardingEvent) -> Unit
) {
    AnimatedContent(
        targetState = state.currentPage,
        modifier = Modifier
            .fillMaxSize(),
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = {
                    it
                }
            ) with slideOutHorizontally(
                targetOffsetX = {
                    -it
                }
            )
        },
        label = stringResource(id = R.string.boarding_label)
    ) { page ->
        when (page) {
            1 -> {
                BoardingContent(
                    imageId = R.drawable.bf_boarding_1,
                    title = stringResource(id = R.string.boarding_1_title),
                    description = stringResource(id = R.string.boarding_1_desc),
                    currentPage = state.currentPage,
                    onNextClick = {
                        onEvent(
                            BoardingEvent.Next
                        )
                    },
                    onStartClick = {
                        onEvent(
                            BoardingEvent.Start
                        )
                    }
                )
            }
            2 -> {
                BoardingContent(
                    imageId = R.drawable.bf_boarding_2,
                    title = stringResource(id = R.string.boarding_2_title),
                    description = stringResource(id = R.string.boarding_2_desc),
                    currentPage = state.currentPage,
                    onNextClick = {
                        onEvent(
                            BoardingEvent.Next
                        )
                    },
                    onStartClick = {
                        onEvent(
                            BoardingEvent.Start
                        )
                    }
                )
            }
            3 -> {
                BoardingContent(
                    imageId = R.drawable.bf_boarding_3,
                    title = stringResource(id = R.string.boarding_3_title),
                    description = stringResource(id = R.string.boarding_3_desc),
                    currentPage = state.currentPage,
                    onNextClick = {
                        onEvent(
                            BoardingEvent.Next
                        )
                    },
                    onStartClick = {
                        onEvent(
                            BoardingEvent.Start
                        )
                    }
                )
            }
        }
    }
}