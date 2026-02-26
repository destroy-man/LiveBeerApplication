package ru.korobeynikov.livebeerapplication.presentation.enter

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class EnterViewModel : ViewModel() {

    private var _phone = MutableStateFlow(TextFieldValue("+7 "))
    val phone: Flow<TextFieldValue> = _phone

    fun changePhone(value: TextFieldValue, minLength: Int, maxLength: Int) {
        if (value.text.length in minLength..maxLength) {
            _phone.value = value
        }
    }
}