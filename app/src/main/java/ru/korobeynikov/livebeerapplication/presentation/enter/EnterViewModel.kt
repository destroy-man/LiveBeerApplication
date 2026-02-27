package ru.korobeynikov.livebeerapplication.presentation.enter

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.korobeynikov.livebeerapplication.domain.UserRepository
import javax.inject.Inject

@HiltViewModel
class EnterViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private var _phone = MutableStateFlow(TextFieldValue("+7 "))
    val phone: Flow<TextFieldValue> = _phone

    private var _error = MutableStateFlow(false)
    val error: Flow<Boolean> = _error

    fun changePhone(value: TextFieldValue, minLength: Int, maxLength: Int) {
        if (value.text.length in minLength..maxLength) {
            _error.value=false
            _phone.value = value
        }
    }

    fun enterInApplication(onSuccess:()-> Unit){
        viewModelScope.launch {
            val phoneValue=_phone.value.text
            if(userRepository.getUsersByPhone(phoneValue)==null){
                _error.value=true
            } else {
                _error.value=false
                onSuccess()
            }
        }
    }
}