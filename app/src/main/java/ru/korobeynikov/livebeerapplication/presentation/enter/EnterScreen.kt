package ru.korobeynikov.livebeerapplication.presentation.enter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.korobeynikov.livebeerapplication.presentation.PhoneVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterScreen(navController: NavController, enterViewModel: EnterViewModel = viewModel()) {
    val onBack: () -> Unit = {
        navController.popBackStack()
    }
    val onRegistration = {
        navController.navigate("registration")
    }

    val colorCyan = Color(0xFF007AFF)
    val colorDarkGrey = Color(0xFF8E8E93)
    val colorLightGrey = Color(0xFFF0F0F0)
    val colorYellow = Color(0xFFFFE100)

    Scaffold(topBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = colorCyan
                )
            }
            Text("Назад", color = colorCyan, modifier = Modifier.offset((-15).dp))
        }
    }) { paddingValues ->
        Column(modifier = Modifier.padding(top = paddingValues.calculateTopPadding() + 8.dp)) {
            Text(
                "Введите ваш номер телефона",
                fontSize = 36.sp,
                modifier = Modifier.padding(start = 24.dp, end = 37.dp)
            )
            Text(
                "Мы вышлем вам проверочный код",
                fontSize = 15.sp,
                modifier = Modifier.padding(start = 24.dp, top = 8.dp),
                color = colorDarkGrey
            )

            val phone by enterViewModel.phone.collectAsState(TextFieldValue("+7 "))
            val minLength = 3
            val maxLength = 13
            BasicTextField(
                phone,
                onValueChange = {
                    enterViewModel.changePhone(it, minLength, maxLength)
                },
                visualTransformation = PhoneVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(fontSize = 38.sp),
                modifier = Modifier.padding(start = 24.dp, top = 24.dp)
            )

            Button(
                enabled = phone.text.length == maxLength,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (phone.text.length < maxLength) colorLightGrey else colorYellow,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 85.dp)
                    .fillMaxWidth(),
                onClick = {}
            ) {
                Text("Далее")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("У вас нет аккаунта? ")
                Text(
                    "Регистрация",
                    color = colorCyan,
                    modifier = Modifier.clickable(onClick = onRegistration)
                )
            }
        }
    }
}