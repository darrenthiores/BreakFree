package com.binus.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.binus.core_ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var name by mutableStateOf("")
        private set

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Next -> {
                _state.update {
                    it.copy(
                        currentSection = event.section
                    )
                }
            }
            is RegisterEvent.OnNameChange -> {
                name = event.name
            }
            is RegisterEvent.OnSelectBirthday -> {
                _state.update {
                    it.copy(
                        birthday = event.birthday
                    )
                }
            }
            is RegisterEvent.OnSelectGender -> {
                _state.update {
                    it.copy(
                        gender = event.gender
                    )
                }
            }
        }
    }
}