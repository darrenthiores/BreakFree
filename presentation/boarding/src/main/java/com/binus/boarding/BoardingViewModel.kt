package com.binus.boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binus.core.domain.preferences.Preferences
import com.binus.core_ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardingViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {
    private val _state = MutableStateFlow(BoardingState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: BoardingEvent) {
        when (event) {
            BoardingEvent.Next -> {
                _state.update {
                    it.copy(
                        currentPage = it.currentPage + 1
                    )
                }
            }
            BoardingEvent.Start -> {
                viewModelScope.launch {
                    preferences
                        .saveShowBoarding(
                            showBoarding = false
                        )

                    _uiEvent.send(
                        UiEvent.Success()
                    )
                }
            }
        }
    }
}