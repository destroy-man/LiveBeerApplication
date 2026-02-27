package ru.korobeynikov.livebeerapplication.presentation.registration

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class RegistrationViewModel : ViewModel() {

    private var _phone = MutableStateFlow(TextFieldValue("+7 "))
    val phone: Flow<TextFieldValue> = _phone

    private var _fullName = MutableStateFlow("")
    val fullName: Flow<String> = _fullName

    private var _birthDate = MutableStateFlow("ДД.ММ.ГГ")
    val birthDate: Flow<String> = _birthDate

    private var _isAgree = MutableStateFlow(false)
    val isAgree: Flow<Boolean> = _isAgree

    fun changePhone(value: TextFieldValue, minLength: Int, maxLength: Int) {
        if (value.text.length in minLength..maxLength) {
            _phone.value = value
        }
    }

    fun changeFullName(value: String) {
        _fullName.value = value
    }


    fun changeBirthDate(day: Int, month: Int, year: Int) {
        val dayStr=if(day<10)"0$day" else day.toString()
        val monthStr=if(month<10)"0$month" else month.toString()
        val yearStr=if(year<10)"0$year" else year.toString()
        _birthDate.value ="$dayStr.$monthStr.$yearStr"
    }

    fun changeIsAgree(value: Boolean) {
        _isAgree.value = value
    }
}