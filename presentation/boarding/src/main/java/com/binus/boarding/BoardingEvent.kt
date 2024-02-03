package com.binus.boarding

sealed interface BoardingEvent {
    data object Next: BoardingEvent
    data object Start: BoardingEvent
}